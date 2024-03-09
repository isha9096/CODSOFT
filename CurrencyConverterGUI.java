import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Class representing the Currency Converter GUI
public class CurrencyConverterGUI extends JFrame implements ActionListener {
    private JTextField amountField;
    private JComboBox<String> baseCurrencyComboBox, targetCurrencyComboBox;
    private JButton convertButton;
    private JLabel resultLabel;

    // Constructor to initialize the GUI components
    public CurrencyConverterGUI() {
        setTitle("Currency Converter");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(0, 2, 10, 10));

        // Components initialization
        JLabel amountLabel = new JLabel("Enter amount:");
        amountField = new JTextField();
        JLabel baseCurrencyLabel = new JLabel("Base Currency:");
        baseCurrencyComboBox = new JComboBox<>(new String[]{"USD", "EUR", "GBP", "INR"}); // Adding INR
        JLabel targetCurrencyLabel = new JLabel("Target Currency:");
        targetCurrencyComboBox = new JComboBox<>(new String[]{"USD", "EUR", "GBP", "INR"}); // Adding INR
        convertButton = new JButton("Convert");
        convertButton.addActionListener(this);
        resultLabel = new JLabel("");

        // Adding components to the frame
        add(amountLabel);
        add(amountField);
        add(baseCurrencyLabel);
        add(baseCurrencyComboBox);
        add(targetCurrencyLabel);
        add(targetCurrencyComboBox);
        add(convertButton);
        add(resultLabel);

        setVisible(true);
    }

    // ActionListener implementation for handling button click events
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == convertButton) {
            double amount;
            try {
                amount = Double.parseDouble(amountField.getText());
            } catch (NumberFormatException ex) {
                resultLabel.setText("Invalid input for amount!");
                return;
            }

            String baseCurrency = (String) baseCurrencyComboBox.getSelectedItem();
            String targetCurrency = (String) targetCurrencyComboBox.getSelectedItem();

            // Perform currency conversion
            double convertedAmount = convertCurrency(amount, baseCurrency, targetCurrency);

            // Display result
            resultLabel.setText(String.format("Converted amount: %.2f %s", convertedAmount, targetCurrency));
        }
    }

    // Method to simulate currency conversion (replace with real API call)
    private double convertCurrency(double amount, String baseCurrency, String targetCurrency) {
        // Example exchange rates (replace with real rates)
        double usdToEurRate = 0.82;
        double usdToGbpRate = 0.73;
        double usdToInrRate = 73.96; // 1 USD = 73.96 INR
        double eurToUsdRate = 1.22;
        double eurToGbpRate = 0.91;
        double eurToInrRate = 89.85; // 1 EUR = 89.85 INR
        double gbpToUsdRate = 1.37;
        double gbpToEurRate = 1.10;
        double gbpToInrRate = 102.04; // 1 GBP = 102.04 INR
        double inrToUsdRate = 0.0135; // 1 INR = 0.0135 USD
        double inrToEurRate = 0.0111; // 1 INR = 0.0111 EUR
        double inrToGbpRate = 0.0098; // 1 INR = 0.0098 GBP

        // Conversion logic
        if (baseCurrency.equals("USD")) {
            if (targetCurrency.equals("EUR")) {
                return amount * usdToEurRate;
            } else if (targetCurrency.equals("GBP")) {
                return amount * usdToGbpRate;
            } else if (targetCurrency.equals("INR")) {
                return amount * usdToInrRate;
            }
        } else if (baseCurrency.equals("EUR")) {
            if (targetCurrency.equals("USD")) {
                return amount * eurToUsdRate;
            } else if (targetCurrency.equals("GBP")) {
                return amount * eurToGbpRate;
            } else if (targetCurrency.equals("INR")) {
                return amount * eurToInrRate;
            }
        } else if (baseCurrency.equals("GBP")) {
            if (targetCurrency.equals("USD")) {
                return amount * gbpToUsdRate;
            } else if (targetCurrency.equals("EUR")) {
                return amount * gbpToEurRate;
            } else if (targetCurrency.equals("INR")) {
                return amount * gbpToInrRate;
            }
        } else if (baseCurrency.equals("INR")) {
            if (targetCurrency.equals("USD")) {
                return amount * inrToUsdRate;
            } else if (targetCurrency.equals("EUR")) {
                return amount * inrToEurRate;
            } else if (targetCurrency.equals("GBP")) {
                return amount * inrToGbpRate;
            }
        }

        return amount; // If same currency selected or unsupported conversion
    }

    // Main method to start the program
    public static void main(String[] args) {
        new CurrencyConverterGUI();
    }
}
