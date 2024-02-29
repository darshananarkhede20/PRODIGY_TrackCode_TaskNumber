import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;

public class AmazonScraperTask5 {

    public static void main(String[] args) {
        String url = "https://www.amazon.com/s?k=laptop"; // URL of the Amazon search results page for laptops

        try {
            Document doc = Jsoup.connect(url).get();

            Elements products = doc.select("div[data-component-type='s-search-result']");

            // Create a CSV file to store the product information
            FileWriter csvWriter = new FileWriter("amazon_products.csv");
            csvWriter.append("Name,Price,Rating\n"); // CSV header

            for (Element product : products) {
                String name = product.select("h2.a-text-normal").text();
                String price = product.select("span.a-price > span.a-offscreen").text().replaceAll("\\$", "");
                String rating = product.select("span.a-icon-alt").text().split(" ")[0]; // Extract rating from alt text

                // Write product information to CSV
                csvWriter.append(name).append(",").append(price).append(",").append(rating).append("\n");
            }

            csvWriter.flush();
            csvWriter.close();

            System.out.println("Product information scraped and saved to amazon_products.csv");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
