
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PNGtoJPGConverter {
    static String fileName="";
    public static void main(String[] args) {
        show();
    }

    static void show(){
        JFrame frame = new JFrame("PNG to JPG converter");
        JPanel p = new JPanel( new FlowLayout(FlowLayout.LEFT));
        JButton btnBrowse = new JButton("Browse");

        btnBrowse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileName=browse();
            }
        });
        JButton btnConvert = new JButton("Convert");
        btnConvert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convert(fileName);
            }
        });
        p.setBounds(10,10, 100, 50);
        btnBrowse.setLayout(null);
        btnConvert.setLayout(null);
        btnBrowse.setLocation( 0,0);
        btnConvert.setLocation( 100,10);
        p.add(btnBrowse);
        p.add(btnConvert);

        frame.add(p, BorderLayout.PAGE_START);
        frame.setSize(400,80);
        frame.setLocation(300,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);


    }
    static String browse(){
        JFileChooser fc = new JFileChooser();
        fc.setFileFilter(new FileNameExtensionFilter(
                "PNG Images", "png"));
        String fName ="";
        int rVal = fc.showOpenDialog(null);
        if(rVal == JFileChooser.APPROVE_OPTION) fName = fc.getSelectedFile().getAbsolutePath();
        return fName.substring(0,fName.lastIndexOf("."));
    }

    static void convert(String fN){
        try{
            File in = new File(fN+".png");
            File out = new File(fN+"-toJPG.jpg");
            BufferedImage img = ImageIO.read(in);
            BufferedImage res = new BufferedImage(img.getWidth(),img.getHeight(), BufferedImage.TYPE_INT_RGB);
            res.createGraphics().drawImage(img,0,0, Color.WHITE, null);
            ImageIO.write(res,"jpg",out);
            fileName="";
            JOptionPane.showConfirmDialog(null, "Convert next?", "Sucessfully converted!", JOptionPane.OK_CANCEL_OPTION );
        }
        catch (IOException ioe){
            ioe.printStackTrace();
            JOptionPane.showMessageDialog(null,"File not exist! Try to browse again.");
            System.out.println("Reading error, browse again please! "+fN+"..");
            fileName=browse();
        }
    }
}
