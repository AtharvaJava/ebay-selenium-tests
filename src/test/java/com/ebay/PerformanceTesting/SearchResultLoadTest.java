
package com.ebay.PerformanceTesting;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.jfree.chart.*;
import org.jfree.data.category.DefaultCategoryDataset;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.ebay.base.DriverFactory;
import com.ebay.pages.SearchResult;

public class SearchResultLoadTest {

    WebDriver driver;
    private static final Map<Long, Long> searchDurations = new HashMap<>();
    private static final String timestamp = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss").format(new Date());

    SearchResult searchResult;
    private JFreeChart chart;
    private ChartPanel chartPanel;
    private JFrame frame;
    private DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    @Test
    public void SearchResultPage() {
    	
        SwingUtilities.invokeLater(this::setupLiveChart);

        try {
            driver = new ChromeDriver();
            DriverFactory.setDriver(driver);
            searchResult = new SearchResult(driver);

            driver.get("https://www.ebay.com");
            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();

            driver.findElement(By.id("gh-ac")).sendKeys("Laptop");
            Thread.sleep(3000);
            driver.findElement(By.id("gh-search-btn")).click();
            Thread.sleep(4000);

            WebElement paginationContainer = driver.findElement(By.className("s-pagination__container"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", paginationContainer);

            for (int i = 0; i < 8; i++) {
                try {
                    List<WebElement> pageLinks = driver.findElements(By.xpath("//ol[@class='pagination__items']//li//a"));

                    long startTime = System.currentTimeMillis();
                    pageLinks.get(i).click();
                    Thread.sleep(4000);
                    driver.navigate().back();
                    Thread.sleep(5000);
                    long endTime = System.currentTimeMillis();

                    long duration = endTime - startTime;
                    searchDurations.put((long) (i + 1), duration);
                    LogtoCsv_SearchResult(i + 1, String.valueOf(duration));
                    updateChart(i + 1, duration);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DriverFactory.removeDriver();

            synchronized (SearchResultLoadTest.class) {
                generateSummaryStatistics();
                chartPanel.repaint();

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                captureChartScreenshot("Ebay/reports/LiveChartScreenshot_" + timestamp + ".png");

                frame.setAlwaysOnTop(true);
                frame.toFront();
                frame.repaint();

                JOptionPane.showMessageDialog(null, "Test complete. Close this window to exit.");
            }
        }
    }

    private void setupLiveChart() {
        chart = ChartFactory.createBarChart(
                "Search Duration per Pagination",
                "Pagination Number",
                "Duration (ms)",
                dataset
        );

        chartPanel = new ChartPanel(chart);
        frame = new JFrame("Live Search Duration Chart");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(chartPanel, BorderLayout.CENTER);
        frame.setSize(800, 600);
        frame.setVisible(true);
    }

    private void updateChart(int paginationNumber, long duration) {
        dataset.addValue(duration, "Duration", String.valueOf(paginationNumber));
        chart.fireChartChanged();
    }

    public synchronized void LogtoCsv_SearchResult(int paginationNumber, String durations) {
        String filePath = "Ebay/reports/PerformanceResult_SearchResultPage_" + timestamp + ".csv";
        File file = new File(filePath);
        file.getParentFile().mkdirs();
        boolean fileExists = file.exists();

        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, true))) {
            if (!fileExists) {
                writer.println("Pagination,Duration(ms)");
            }
            writer.println(paginationNumber + "," + durations);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void generateSummaryStatistics() {
        DescriptiveStatistics stats = new DescriptiveStatistics();
        for (long duration : searchDurations.values()) {
            stats.addValue(duration);
        }

        System.out.println("\n--- Summary Statistics ---");
        System.out.println("Mean: " + stats.getMean());
        System.out.println("Min: " + stats.getMin());
        System.out.println("Max: " + stats.getMax());
        System.out.println("Std Dev: " + stats.getStandardDeviation());
    }

    private void captureChartScreenshot(String filePath) {
        try {
            BufferedImage image = new BufferedImage(chartPanel.getWidth(), chartPanel.getHeight(), BufferedImage.TYPE_INT_ARGB);
            chartPanel.paint(image.getGraphics());
            File outputFile = new File(filePath);
            ImageIO.write(image, "png", outputFile);
            System.out.println("Live chart screenshot saved to: " + outputFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
