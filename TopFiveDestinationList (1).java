import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class TopFiveDestinationList {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TopDestinationListFrame topDestinationListFrame = new TopDestinationListFrame();
                topDestinationListFrame.setTitle("Top 5 Destination List");
                topDestinationListFrame.setVisible(true);
            }
        });
    }
}

class TopDestinationListFrame extends JFrame {
    private DefaultListModel listModel;

    public TopDestinationListFrame() {
        super("Top Five Destination List");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(900, 750);

        // Added assignment author label.
        JLabel nameLabel = new JLabel("Created by Elijah Gross", SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setOpaque(true);
        nameLabel.setBackground(new Color(30, 60, 90));
        getContentPane().add(nameLabel, BorderLayout.NORTH);

        listModel = new DefaultListModel();

        // Updated destination list with personalized travel locations, descriptions, links, and images.
        addDestinationNameAndPicture(
            "<html><b>1. Panama City Beach, Florida</b><br>Beautiful beaches, nightlife, and relaxing coastal weather.<br><u>View top-selling travel package</u></html>",
            resizeImage("/resources/panamacity.jpg")
        );

        addDestinationNameAndPicture(
            "<html><b>2. Hocking Hills, Ohio</b><br>Peaceful cabins, hiking trails, and quiet forest scenery.<br><u>View top-selling travel package</u></html>",
            resizeImage("/resources/hockinghills.jpg")
        );

        addDestinationNameAndPicture(
            "<html><b>3. London, England</b><br>Historic landmarks, museums, and modern city attractions.<br><u>View top-selling travel package</u></html>",
            resizeImage("/resources/london.jpg")
        );

        addDestinationNameAndPicture(
            "<html><b>4. Amsterdam, Netherlands</b><br>Relaxing canals, culture, and unique city experiences.<br><u>View top-selling travel package</u></html>",
            resizeImage("/resources/amsterdam.jpg")
        );

        addDestinationNameAndPicture(
            "<html><b>5. Marrakech, Morocco</b><br>Peaceful desert scenery, camel rides, and relaxing cultural experiences.<br><u>View top-selling travel package</u></html>",
            resizeImage("/resources/morocco.jpg")
        );

        JList list = new JList(listModel);

        // Customized the ListView appearance with a travel-themed color scheme and larger list rows.
        list.setBackground(new Color(220, 235, 250));
        list.setFont(new Font("Arial", Font.PLAIN, 16));
        list.setFixedCellHeight(140);

        JScrollPane scrollPane = new JScrollPane(list);

        TextAndIconListCellRenderer renderer = new TextAndIconListCellRenderer(8);
        list.setCellRenderer(renderer);

        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }
    private ImageIcon resizeImage(String path) {
        ImageIcon icon = new ImageIcon(getClass().getResource(path));
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(300, 150, Image.SCALE_SMOOTH);
        return new ImageIcon(newImage);
    }
    private void addDestinationNameAndPicture(String text, Icon icon) {
        TextAndIcon tai = new TextAndIcon(text, icon);
        listModel.addElement(tai);
    }
}

class TextAndIcon {
    private String text;
    private Icon icon;

    public TextAndIcon(String text, Icon icon) {
        this.text = text;
        this.icon = icon;
    }

    public String getText() {
        return text;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }
}

class TextAndIconListCellRenderer extends JLabel implements ListCellRenderer {
    private static final Border NO_FOCUS_BORDER = new EmptyBorder(1, 1, 1, 1);

    private Border insideBorder;

    public TextAndIconListCellRenderer() {
        this(0, 0, 0, 0);
    }

    public TextAndIconListCellRenderer(int padding) {
        this(padding, padding, padding, padding);
    }

    public TextAndIconListCellRenderer(int topPadding, int rightPadding, int bottomPadding, int leftPadding) {
        insideBorder = BorderFactory.createEmptyBorder(topPadding, leftPadding, bottomPadding, rightPadding);
        setOpaque(true);
    }

    public Component getListCellRendererComponent(JList list, Object value,
    int index, boolean isSelected, boolean hasFocus) {
        TextAndIcon tai = (TextAndIcon) value;

        setText(tai.getText());
        setIcon(tai.getIcon());

        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        Border outsideBorder;

        if (hasFocus) {
            outsideBorder = UIManager.getBorder("List.focusCellHighlightBorder");
        } else {
            outsideBorder = NO_FOCUS_BORDER;
        }

        setBorder(BorderFactory.createCompoundBorder(outsideBorder, insideBorder));
        setComponentOrientation(list.getComponentOrientation());
        setEnabled(list.isEnabled());
        setFont(list.getFont());

        return this;
    }

    public void validate() {}
    public void invalidate() {}
    public void repaint() {}
    public void revalidate() {}
    public void repaint(long tm, int x, int y, int width, int height) {}
    public void repaint(Rectangle r) {}
}