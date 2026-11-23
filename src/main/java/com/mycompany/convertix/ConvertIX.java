/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.convertix;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.Map;
/**
 *
 * @author Administrator
 */
  
public class ConvertIX extends JFrame {

    
    Color BG = new Color(20, 31, 75);
    Color PANEL = new Color(39, 57, 112);
    Color PANEL2 = new Color(49, 70, 133);
    Color PURPLE = new Color(124, 75, 245);
    Color WHITE = new Color(245, 247, 255);
    Color MUTED = new Color(190, 202, 235);
    Color GREEN = new Color(35, 225, 177);

    Map<String, String[]> units = new LinkedHashMap<>();

    String category = "Volume";

    JTextField input;
    JComboBox<String> fromBox, toBox;
    JLabel resultValue, resultUnit, status;
    JPanel categoryPanel;

    
    public ConvertIX() {

        setTitle("ConvertIX - Smart Measurement Converter");
        setSize(1100, 720);
        setMinimumSize(new Dimension(800, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        loadUnits();
        buildUI();
        convert();
    }

    
    private void loadUnits() {

        units.put("Length", new String[]{
            "Meters (m)", "Kilometers (km)",
            "Centimeters (cm)", "Millimeters (mm)",
            "Miles (mi)", "Feet (ft)", "Inches (in)"
        });

        units.put("Weight", new String[]{
            "Kilograms (kg)", "Grams (g)",
            "Milligrams (mg)", "Pounds (lb)",
            "Ounces (oz)", "Metric tons (t)"
        });

        units.put("Temperature", new String[]{
            "Celsius (°C)", "Fahrenheit (°F)",
            "Kelvin (K)"
        });

        units.put("Area", new String[]{
            "Square meters (m²)",
            "Square kilometers (km²)",
            "Square centimeters (cm²)",
            "Hectares (ha)", "Acres (ac)",
            "Square feet (ft²)"
        });

        units.put("Volume", new String[]{
            "Milliliters (mL)", "Liters (L)",
            "Cubic meters (m³)",
            "Cubic centimeters (cm³)",
            "Gallons (US)", "Cups (US)"
        });

        units.put("Speed", new String[]{
            "Meters/second (m/s)",
            "Kilometers/hour (km/h)",
            "Miles/hour (mph)", "Knots (kn)"
        });

        units.put("Time", new String[]{
            "Seconds (s)", "Minutes (min)",
            "Hours (h)", "Days (d)",
            "Weeks (wk)"
        });

        units.put("Digital Storage", new String[]{
            "Bytes (B)", "Kilobytes (KB)",
            "Megabytes (MB)", "Gigabytes (GB)",
            "Terabytes (TB)"
        });

        units.put("Pressure", new String[]{
            "Pascals (Pa)", "Kilopascals (kPa)",
            "Bar", "Atmospheres (atm)", "PSI"
        });
    }

    
    private void buildUI() {

        JPanel root = new JPanel(new BorderLayout(18, 18));
        root.setBackground(BG);
        root.setBorder(new EmptyBorder(24, 26, 22, 26));

        setContentPane(root);

        
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);

        JPanel brand = new JPanel(
            new FlowLayout(FlowLayout.LEFT, 14, 0)
        );

        brand.setOpaque(false);

        JLabel logo = new JLabel("CX", SwingConstants.CENTER);
        logo.setOpaque(true);
        logo.setBackground(PURPLE);
        logo.setForeground(WHITE);
        logo.setFont(new Font("SansSerif", Font.BOLD, 23));
        logo.setPreferredSize(new Dimension(58, 54));

        JPanel names = new JPanel();
        names.setOpaque(false);
        names.setLayout(new BoxLayout(names, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("ConvertIX");
        title.setForeground(WHITE);
        title.setFont(new Font("SansSerif", Font.BOLD, 28));

        JLabel subtitle = new JLabel(
            "Smart Measurement Converter"
        );

        subtitle.setForeground(MUTED);

        names.add(title);
        names.add(subtitle);

        brand.add(logo);
        brand.add(names);

        JLabel ready = new JLabel("READY");
        ready.setForeground(GREEN);
        ready.setFont(new Font("SansSerif", Font.BOLD, 13));

        ready.setBorder(
            new CompoundBorder(
                new LineBorder(new Color(80, 112, 170)),
                new EmptyBorder(12, 16, 12, 16)
            )
        );

        header.add(brand, BorderLayout.WEST);
        header.add(ready, BorderLayout.EAST);

        root.add(header, BorderLayout.NORTH);

        
        JPanel main = new JPanel();
        main.setOpaque(false);
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

        JLabel catLabel = new JLabel(
            "MEASUREMENT CATEGORIES"
        );

        catLabel.setForeground(MUTED);
        catLabel.setFont(
            new Font("SansSerif", Font.BOLD, 12)
        );

        main.add(catLabel);
        main.add(Box.createVerticalStrut(10));

        
        categoryPanel = new JPanel(
            new GridLayout(3, 3, 10, 10)
        );

        categoryPanel.setOpaque(false);

        String[] namesList = {
            "Length", "Weight", "Temperature",
            "Area", "Volume", "Speed",
            "Time", "Digital Storage", "Pressure"
        };

        String[] symbols = {
            "↔", "▣", "℃", "▦", "◉",
            "▶", "◷", "▤", "●"
        };

        for (int i = 0; i < namesList.length; i++) {

            String name = namesList[i];

            JButton b = new JButton(
                symbols[i] + "   " + name
            );

            styleCategory(b, name.equals(category));

            b.setHorizontalAlignment(
                SwingConstants.LEFT
            );

            b.setFocusPainted(false);

            b.addActionListener(e ->
                selectCategory(name)
            );

            categoryPanel.add(b);
        }

        main.add(categoryPanel);
        main.add(Box.createVerticalStrut(18));

        
        JPanel convertPanel = new JPanel(
            new BorderLayout(14, 18)
        );

        convertPanel.setBackground(PANEL);

        convertPanel.setBorder(
            new CompoundBorder(
                new LineBorder(new Color(70, 94, 155)),
                new EmptyBorder(22, 22, 20, 22)
            )
        );

        JPanel convertHeader = new JPanel(
            new BorderLayout()
        );

        convertHeader.setOpaque(false);

        JLabel convertTitle = new JLabel(
            "Convert measurement"
        );

        convertTitle.setForeground(WHITE);
        convertTitle.setFont(
            new Font("SansSerif", Font.BOLD, 21)
        );

        JLabel live = new JLabel("● LIVE CONVERSION");
        live.setForeground(GREEN);
        live.setFont(
            new Font("SansSerif", Font.BOLD, 11)
        );

        convertHeader.add(
            convertTitle, BorderLayout.WEST
        );

        convertHeader.add(live, BorderLayout.EAST);

        
        JPanel fields = new JPanel(
            new GridBagLayout()
        );

        fields.setOpaque(false);

        GridBagConstraints c = new GridBagConstraints();

        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;
        c.weighty = 1;
        c.insets = new Insets(12, 0, 8, 0);

        JPanel fromPanel = createValuePanel("FROM");
        JPanel toPanel = createValuePanel("TO");

        input = new JTextField("3000");

        input.setBackground(PANEL2);
        input.setForeground(WHITE);
        input.setCaretColor(WHITE);
        input.setFont(
            new Font("SansSerif", Font.BOLD, 28)
        );

        input.setBorder(
            new EmptyBorder(8, 12, 8, 12)
        );

        fromBox = new JComboBox<>(units.get(category));
        toBox = new JComboBox<>(units.get(category));

        fromBox.setSelectedIndex(0);
        toBox.setSelectedIndex(2);

        styleCombo(fromBox);
        styleCombo(toBox);

        fromPanel.add(input, BorderLayout.CENTER);
        fromPanel.add(fromBox, BorderLayout.SOUTH);

        resultValue = new JLabel("0.003");

        resultValue.setForeground(WHITE);
        resultValue.setFont(
            new Font("SansSerif", Font.BOLD, 29)
        );

        resultValue.setBorder(
            new EmptyBorder(8, 12, 8, 12)
        );

        resultUnit = new JLabel("Cubic meters (m³)");

        resultUnit.setForeground(MUTED);

        resultUnit.setBorder(
            new EmptyBorder(0, 12, 10, 12)
        );

        toPanel.add(resultValue, BorderLayout.CENTER);
        toPanel.add(resultUnit, BorderLayout.SOUTH);

        c.gridx = 0;
        c.weightx = 1;

        fields.add(fromPanel, c);

        
        JButton swap = new JButton("⇄");

        swap.setBackground(PURPLE);
        swap.setForeground(WHITE);
        swap.setFont(
            new Font("SansSerif", Font.BOLD, 24)
        );

        swap.setFocusPainted(false);

        swap.setBorder(
            new EmptyBorder(8, 15, 8, 15)
        );

        swap.addActionListener(e -> {

            int old = fromBox.getSelectedIndex();

            fromBox.setSelectedIndex(
                toBox.getSelectedIndex()
            );

            toBox.setSelectedIndex(old);

            convert();
        });

        c.gridx = 1;
        c.weightx = 0;
        c.insets = new Insets(12, 10, 8, 10);

        fields.add(swap, c);

        c.gridx = 2;
        c.weightx = 1;
        c.insets = new Insets(12, 0, 8, 0);

        fields.add(toPanel, c);

        status = new JLabel(
            "↳ Conversion updates as you type"
        );

        status.setForeground(MUTED);

        JPanel center = new JPanel(
            new BorderLayout(0, 8)
        );

        center.setOpaque(false);

        center.add(convertHeader, BorderLayout.NORTH);
        center.add(fields, BorderLayout.CENTER);
        center.add(status, BorderLayout.SOUTH);

        convertPanel.add(center, BorderLayout.CENTER);

        main.add(convertPanel);

        root.add(main, BorderLayout.CENTER);

        
        JPanel footer = new JPanel(
            new BorderLayout()
        );

        footer.setOpaque(false);

        JLabel footLeft = new JLabel(
            "FAST  •  ACCURATE  •  OFFLINE"
        );

        footLeft.setForeground(MUTED);

        JLabel footRight = new JLabel("ConvertIX 1.0");
        footRight.setForeground(MUTED);

        footer.add(footLeft, BorderLayout.WEST);
        footer.add(footRight, BorderLayout.EAST);

        root.add(footer, BorderLayout.SOUTH);

        
        input.getDocument().addDocumentListener(
            new DocumentListener() {

                public void insertUpdate(DocumentEvent e) {
                    convert();
                }

                public void removeUpdate(DocumentEvent e) {
                    convert();
                }

                public void changedUpdate(DocumentEvent e) {
                    convert();
                }
            }
        );

        fromBox.addActionListener(e -> convert());
        toBox.addActionListener(e -> convert());
    }

    
    private JPanel createValuePanel(String label) {

        JPanel p = new JPanel(
            new BorderLayout(0, 4)
        );

        p.setBackground(PANEL2);

        p.setBorder(
            new LineBorder(new Color(93, 119, 181))
        );

        JLabel l = new JLabel(label);

        l.setForeground(MUTED);
        l.setFont(
            new Font("SansSerif", Font.BOLD, 11)
        );

        l.setBorder(
            new EmptyBorder(12, 12, 0, 12)
        );

        p.add(l, BorderLayout.NORTH);

        return p;
    }

   
    private void styleCombo(JComboBox<String> box) {

        box.setBackground(new Color(29, 45, 94));
        box.setForeground(WHITE);

        box.setFont(
            new Font("SansSerif", Font.PLAIN, 13)
        );

        box.setBorder(
            new EmptyBorder(5, 8, 5, 8)
        );
    }

    
    private void styleCategory(
        JButton b, boolean selected
    ) {

        b.setBackground(
            selected ? PURPLE : PANEL2
        );

        b.setForeground(WHITE);

        b.setFont(
            new Font("SansSerif", Font.BOLD, 14)
        );

        b.setBorder(
            new CompoundBorder(
                new LineBorder(new Color(83, 105, 163)),
                new EmptyBorder(25, 14, 25, 14)
            )
        );
    }

    
    private void selectCategory(String name) {

        category = name;

        for (Component comp :
            categoryPanel.getComponents()) {

            JButton b = (JButton) comp;

            styleCategory(
                b, b.getText().contains(name)
            );
        }

        fromBox.setModel(
            new DefaultComboBoxModel<>(
                units.get(name)
            )
        );

        toBox.setModel(
            new DefaultComboBoxModel<>(
                units.get(name)
            )
        );

        fromBox.setSelectedIndex(0);

        toBox.setSelectedIndex(
            Math.min(1, units.get(name).length - 1)
        );

        convert();
    }

    
    private double factor(String unit) {

        switch (unit) {

            case "Meters (m)":
            case "Kilograms (kg)":
            case "Square meters (m²)":
            case "Liters (L)":
            case "Meters/second (m/s)":
            case "Seconds (s)":
            case "Bytes (B)":
            case "Pascals (Pa)":
                return 1;

            case "Kilometers (km)": return 1000;
            case "Centimeters (cm)": return 0.01;
            case "Millimeters (mm)": return 0.001;
            case "Miles (mi)": return 1609.344;
            case "Feet (ft)": return 0.3048;
            case "Inches (in)": return 0.0254;

            case "Grams (g)": return 0.001;
            case "Milligrams (mg)": return 0.000001;
            case "Pounds (lb)": return 0.45359237;
            case "Ounces (oz)": return 0.0283495;
            case "Metric tons (t)": return 1000;

            case "Square kilometers (km²)": return 1000000;
            case "Square centimeters (cm²)": return 0.0001;
            case "Hectares (ha)": return 10000;
            case "Acres (ac)": return 4046.8564224;
            case "Square feet (ft²)": return 0.09290304;

            case "Milliliters (mL)":
            case "Cubic centimeters (cm³)":
                return 0.001;

            case "Cubic meters (m³)": return 1000;
            case "Gallons (US)": return 3.785411784;
            case "Cups (US)": return 0.2365882365;

            case "Kilometers/hour (km/h)": return 1.0 / 3.6;
            case "Miles/hour (mph)": return 0.44704;
            case "Knots (kn)": return 0.514444;

            case "Minutes (min)": return 60;
            case "Hours (h)": return 3600;
            case "Days (d)": return 86400;
            case "Weeks (wk)": return 604800;

            case "Kilobytes (KB)": return 1024;
            case "Megabytes (MB)":
                return Math.pow(1024, 2);
            case "Gigabytes (GB)":
                return Math.pow(1024, 3);
            case "Terabytes (TB)":
                return Math.pow(1024, 4);

            case "Kilopascals (kPa)": return 1000;
            case "Bar": return 100000;
            case "Atmospheres (atm)": return 101325;
            case "PSI": return 6894.757;

            default: return 1;
        }
    }

    private double convertTemperature(
        double value, String from, String to
    ) {

        double c = value;

        if (from.startsWith("Fahrenheit")) {
            c = (value - 32) * 5 / 9;
        }

        else if (from.startsWith("Kelvin")) {
            c = value - 273.15;
        }

        if (to.startsWith("Fahrenheit")) {
            return c * 9 / 5 + 32;
        }

        if (to.startsWith("Kelvin")) {
            return c + 273.15;
        }

        return c;
    }

    
    private void convert() {

        if (input == null || resultValue == null)
            return;

        try {

            double value = Double.parseDouble(
                input.getText().trim()
            );

            String from =
                (String) fromBox.getSelectedItem();

            String to =
                (String) toBox.getSelectedItem();

            if (from == null || to == null)
                return;

            double answer;

            if (category.equals("Temperature")) {

                answer = convertTemperature(
                    value, from, to
                );

            } else {

                answer =
                    value * factor(from) / factor(to);
            }

            DecimalFormat df =
                new DecimalFormat("#,##0.########");

            resultValue.setText(df.format(answer));
            resultUnit.setText(to);

            status.setText(
                " Conversion updated successfully"
            );

            status.setForeground(GREEN);

        } catch (NumberFormatException ex) {

            resultValue.setText("—");

            resultUnit.setText(
                "Enter a valid number"
            );

            status.setText(
                "! Please enter a valid numeric value"
            );

            status.setForeground(
                new Color(255, 190, 100)
            );
        }
    }

    
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            new ConvertIX().setVisible(true);

        });
    }
} 

    

