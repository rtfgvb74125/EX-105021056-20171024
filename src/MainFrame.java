import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

public class MainFrame extends JFrame{
    private LoginFram lgfm;
    private int screenW = Toolkit.getDefaultToolkit().getScreenSize().width;
    private int screenH = Toolkit.getDefaultToolkit().getScreenSize().height;
    private int frmW = 500, frmH = 400;
    private JButton jbtnClose  = new JButton("Close");
    private JButton jbtnRemake = new JButton("Rerandom");
    private JMenuBar jmb = new JMenuBar();
    private JMenu jmF = new JMenu("File");
    private JMenu jmSet = new JMenu("Set");
    private JMenu jmGame = new JMenu("Game");
    private JMenu jmAbout = new JMenu("About");
    private JMenuItem jmiExit = new JMenuItem("Exit");
    private JMenuItem jmiLotto = new JMenuItem("Lotto");
    private JDesktopPane jdp = new JDesktopPane();
    private JInternalFrame jif = new JInternalFrame();
    private int data[] = new int[6];
    private JLabel jlab[] = new JLabel[6];
    private Random rnd = new Random(System.currentTimeMillis());
    private JPanel jpnNumber = new JPanel(new GridLayout(1,6,3,3));
    private JPanel jpnControl = new JPanel(new GridLayout(1,2,3,3));
    public MainFrame(LoginFram lg){
        lgfm = lg;
        init();
    }
    private void init(){
        this.setBounds(screenW/2-frmW/2,screenH/2-frmH/2,frmW,frmH);
        jif.setBounds(0,0,300,200);
        this.setJMenuBar(jmb);
        this.setContentPane(jdp);
        jmb.add(jmF);
        jmb.add(jmSet);
        jmb.add(jmGame);
        jmb.add(jmAbout);
        jmF.add(jmiExit);
        jmGame.add(jmiLotto);
        jif.setLayout(new BorderLayout(3,3));
        jif.add(jpnControl,BorderLayout.NORTH);
        jif.add(jpnNumber,BorderLayout.CENTER);

        jpnControl.add(jbtnClose);
        jpnControl.add(jbtnRemake);

        jmiExit.setAccelerator(KeyStroke.getKeyStroke('C',Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
    Number();

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                lgfm.setVisible(true);
                dispose();
            }
        });
        //control
        jmiExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        jmiLotto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jdp.add(jif);
                jif.setVisible(true);

                for(int i = 0;i<6;i++){
                    jlab[i] = new JLabel();
                    jpnNumber.add(jlab[i]);
                }
            }
        });

        jbtnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jif.setVisible(false);
            }
        });
    }
    private void Number(){
        int i = 0;
        while(i<6){
            data[i] = rnd.nextInt(42)+1;
            int j = 0;
            boolean flag = true;
            while(j<i && flag){
                if(data[i]==data[j]){
                    flag = false;
                }
                j++;
            } if(flag){
                jlab[i].setText(Integer.toString(data[i]));
                i++;
            }
        }
    }
}

