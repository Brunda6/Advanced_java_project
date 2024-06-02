import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class CurrencyConverterApp extends JFrame {
    private JComboBox<String> fromCurrencyComboBox;
    private JComboBox<String> toCurrencyComboBox;
    private JTextField amountTextField;
    private JLabel resultLabel;
    // Hardcoded exchange rates (for illustration purposes)
    private static final double USD_TO_EUR_RATE = 0.92;
    private static final double USD_TO_GBP_RATE = 0.79;
    private static final double USD_TO_JPY_RATE = 150.09;
    private static final double USD_TO_AUD_RATE = 1.53;
    private static final double USD_TO_CAD_RATE = 1.36;
    private static final double USD_TO_INR_RATE = 82.85;
    private static final double USD_TO_CNY_RATE = 7.20;
    private static final double USD_TO_BRL_RATE = 4.95;
    private static final double USD_TO_RUB_RATE = 91.64;
    private static final double USD_TO_ZAR_RATE = 19.06;

    public CurrencyConverterApp() {
        // Set up the main frame
        setTitle("Currency Converter");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create components
        JLabel fromLabel = new JLabel("From Currency:");
        fromLabel.setFont(new Font("Times New Roman", Font.BOLD, 24)); // Set font size here
        fromCurrencyComboBox = new JComboBox<>(new String[]{"USD  (US)", "EUR  (EUROPE)", "GBP  (UK)", "JPY  (JAPAN)", "AUD  (AUSTRALIA)", "CAD  (CANADA)", "INR  (INDIA)", "CNY  (CHINA)", "BRL  (BRAZIL)", "RUB  (RUSSIA)", "ZAR  (SOUTH AFRICA)"});
        fromCurrencyComboBox.setFont(new Font("Arial", Font.PLAIN, 24)); // Set font size here

        JLabel toLabel = new JLabel("To Currency:");
        toLabel.setFont(new Font("Times New Roman", Font.BOLD, 24)); // Set font size here
        toCurrencyComboBox = new JComboBox<>(new String[]{"USD  (US)", "EUR  (EUROPE)", "GBP  (UK)", "JPY  (JAPAN)", "AUD  (AUSTRALIA)", "CAD  (CANADA)", "INR  (INDIA)", "CNY  (CHINA)", "BRL  (BRAZIL)", "RUB  (RUSSIA)", "ZAR  (SOUTH AFRICA)"});
        toCurrencyComboBox.setFont(new Font("Arial", Font.PLAIN, 24)); // Set font size here

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setFont(new Font("Times New Roman", Font.BOLD, 24)); // Set font size here
        amountTextField = new JTextField(20);
        amountTextField.setFont(new Font("Times New Roman", Font.BOLD, 24));// Set input font size here

        JButton convertButton = new JButton("Convert");
        convertButton.setFont(new Font("Times New Roman", Font.BOLD, 24)); // Set font size here
        convertButton.addActionListener(new ConvertButtonListener());

        resultLabel = new JLabel("Result:");
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 24)); // Set font size here

        // Set up the layout
        setLayout(new GridLayout(4, 2));

        // Add components to the frame
        add(fromLabel);
        add(fromCurrencyComboBox);
        add(toLabel);
        add(toCurrencyComboBox);
        add(amountLabel);
        add(amountTextField);
        add(convertButton);
        add(resultLabel);

        // Display the frame
        setVisible(true);
    }

    private class ConvertButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // Get user input
                double amount = Double.parseDouble(amountTextField.getText());
                String fromCurrency = (String) fromCurrencyComboBox.getSelectedItem();
                String toCurrency = (String) toCurrencyComboBox.getSelectedItem();

                // Perform conversion
                double result = convertCurrency(amount, fromCurrency, toCurrency);

                // Display result
                DecimalFormat decimalFormat = new DecimalFormat("#.##");
                resultLabel.setText("Result: " + decimalFormat.format(result) + " " + toCurrency);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number for the amount.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private double convertCurrency(double amount, String fromCurrency, String toCurrency) {
        double rate;
        switch (fromCurrency) {
            case "USD  (US)":
                rate = getConversionRate(toCurrency);
                break;
            case "EUR  (EUROPE)":
            case "GBP  (UK)":
            case "JPY  (JAPAN)":
            case "AUD  (AUSTRALIA)":
            case "CAD  (CANADA)":
            case "INR  (INDIA)":
            case "CNY  (CHINA)":
            case "BRL  (BRAZIL)":
            case "RUB  (RUSSIA)":
            case "ZAR  (SOUTH AFRICA)":
                rate = 1 / getConversionRate(fromCurrency);
                break;
            default:
                rate = 1.0;
                break;
        }
        return amount * rate;
    }
    private double getConversionRate(String currency) {
        switch (currency) {
            case "EUR  (EUROPE)":
                return USD_TO_EUR_RATE;
            case "GBP  (UK)":
                return USD_TO_GBP_RATE;
            case "JPY  (JAPAN)":
                return USD_TO_JPY_RATE;
            case "AUD  (AUSTRALIA)":
                return USD_TO_AUD_RATE;
            case "CAD  (CANADA)":
                return USD_TO_CAD_RATE;
            case "INR  (INDIA)":
                return USD_TO_INR_RATE;
            case "CNY  (CHINA)":
                return USD_TO_CNY_RATE;
            case "BRL  (BRAZIL)":
                return USD_TO_BRL_RATE;
            case "RUB  (RUSSIA)":
                return USD_TO_RUB_RATE;
            case "ZAR  (SOUTH AFRICA)":
                return USD_TO_ZAR_RATE;
            default:
                return 1.0;
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CurrencyConverterApp());
    }
}
