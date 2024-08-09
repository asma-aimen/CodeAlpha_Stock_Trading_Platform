import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Stock {
    private String symbol;
    private double price;

    public Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

class Portfolio {
    private HashMap<String, Integer> stocks;
    private double balance;

    public Portfolio(double initialBalance) {
        this.stocks = new HashMap<>();
        this.balance = initialBalance;
    }

    public void buyStock(String symbol, int quantity, double price) {
        if (balance >= price * quantity) {
            stocks.put(symbol, stocks.getOrDefault(symbol, 0) + quantity);
            balance -= price * quantity;
            System.out.println("Bought " + quantity + " shares of " + symbol + " at $" + price + " each.");
        } else {
            System.out.println("Insufficient balance to buy stocks.");
        }
    }

    public void sellStock(String symbol, int quantity, double price) {
        if (stocks.getOrDefault(symbol, 0) >= quantity) {
            stocks.put(symbol, stocks.get(symbol) - quantity);
            balance += price * quantity;
            System.out.println("Sold " + quantity + " shares of " + symbol + " at $" + price + " each.");
        } else {
            System.out.println("Insufficient shares to sell.");
        }
    }

    public double getBalance() {
        return balance;
    }

    public HashMap<String, Integer> getStocks() {
        return stocks;
    }

    public void displayPortfolio() {
        System.out.println("Portfolio Balance: $" + balance);
        System.out.println("Stocks: " + stocks);
    }
}

public class StockTradingPlatform {
    private static ArrayList<Stock> marketData = new ArrayList<>();

    public static void initializeMarketData() {
        marketData.add(new Stock("AAPL", 150.00));
        marketData.add(new Stock("GOOG", 2800.00));
        marketData.add(new Stock("MSFT", 300.00));
        marketData.add(new Stock("AMZN", 3500.00));
    }

    public static Stock getStock(String symbol) {
        for (Stock stock : marketData) {
            if (stock.getSymbol().equalsIgnoreCase(symbol)) {
                return stock;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Portfolio portfolio = new Portfolio(10000.00);
        initializeMarketData();

        while (true) {
            System.out.println("\n--- Stock Trading Platform ---");
            System.out.println("1. View Market Data");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\nMarket Data:");
                    for (Stock stock : marketData) {
                        System.out.println(stock.getSymbol() + ": $" + stock.getPrice());
                    }
                    break;
                case 2:
                    System.out.print("Enter stock symbol to buy: ");
                    String buySymbol = scanner.next();
                    Stock buyStock = getStock(buySymbol);
                    if (buyStock != null) {
                        System.out.print("Enter quantity to buy: ");
                        int buyQuantity = scanner.nextInt();
                        portfolio.buyStock(buySymbol, buyQuantity, buyStock.getPrice());
                    } else {
                        System.out.println("Stock not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter stock symbol to sell: ");
                    String sellSymbol = scanner.next();
                    Stock sellStock = getStock(sellSymbol);
                    if (sellStock != null) {
                        System.out.print("Enter quantity to sell: ");
                        int sellQuantity = scanner.nextInt();
                        portfolio.sellStock(sellSymbol, sellQuantity, sellStock.getPrice());
                    } else {
                        System.out.println("Stock not found.");
                    }
                    break;
                case 4:
                    portfolio.displayPortfolio();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
