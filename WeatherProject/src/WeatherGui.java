import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class WeatherGui extends JFrame {
	
	
	
	int index2 = 0;

	double[] tmaxAvgAry;
	double[] tmaxMaxAry;
	double[] tmaxMinAry;

	double[] tminAvgAry;
	double[] tminMaxAry;
	double[] tminMinAry;

	double[] afAvgAry;
	double[] afMaxAry;
	double[] afMinAry;

	double[] rainAvgAry;
	double[] rainMaxAry;
	double[] rainMinAry;

	double[] sunAvgAry;
	double[] sunMaxAry;
	double[] sunMinAry;

	double year[]= new double[805];
	double month[] = new double[805];
	double tmax[] = new double[805];
	double tmin[]= new double[805];
	double af[] = new double[805];
	double rain[] = new double[805];
	double sun[] = new double[805];

	
	
	private JPanel contentPane;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WeatherGui frame = new WeatherGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	
	}

	/**
	 * Create the frame.
	 */
	public WeatherGui()    {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent arg0) {
			

			}
		});
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnPlotData = new JButton("Process Data");
		btnPlotData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				

				
				WeatherPlot.plot(year,month,tmax,tmin,af,rain,sun);
				

			}
			
			
			
		});
		
		
		
		btnPlotData.setBounds(10, 92, 124, 40);
		contentPane.add(btnPlotData);
		
		JLabel lblNewLabel = new JLabel("Sheffield Data");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(146, 11, 124, 30);
		contentPane.add(lblNewLabel);
		
		JButton btnRain = new JButton("Total Rainfall ");
		btnRain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WeatherPlot.rainMethod(year, month, rain);
			}
		});
		btnRain.setBounds(144, 123, 244, 23);
		contentPane.add(btnRain);
		
		JButton btnSun = new JButton("Sun Duration");
		btnSun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WeatherPlot.sunMethod(year, month,sun);
			}
		});
		btnSun.setBounds(10, 145, 124, 35);
		contentPane.add(btnSun);
		

		
		JButton btnTemperatureMinimum = new JButton("Temperature Minimum");
		btnTemperatureMinimum.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent arg0)   {
				WeatherPlot.tminMethod(year,month,tmin);
				

			}
		});
		btnTemperatureMinimum.setBounds(146, 157, 242, 23);
		contentPane.add(btnTemperatureMinimum);
		
		JButton btnAirFrost = new JButton("Air Frost");
		btnAirFrost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				WeatherPlot.afMethod(year, month, af);
			}
		});
		btnAirFrost.setBounds(144, 92, 95, 23);
		contentPane.add(btnAirFrost);
		
		JButton btnTempMax = new JButton("Temp max");
		btnTempMax.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			
				WeatherPlot.tmaxMethod(year, month, tmax);
			
				

				
			}
		});
		btnTempMax.setBounds(253, 92, 135, 23);
		contentPane.add(btnTempMax);
	}
	

}
	
	
