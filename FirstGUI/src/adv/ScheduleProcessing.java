package adv;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ScheduleProcessing implements ActionListener{
	
	private static int n=0;
	private static int[] btime = new int[10];
	private static int[] atime = new int[10];
	private static int[] ct = new int[10];
	private static int [] tt = new int[10];
	private static int [] wt = new int[10];
	private static int[] procid= new int[10];
	
	Scanner sc = new Scanner(System.in);
	
	private static JLabel label1,label2;
	private static JTextField id ,at,bt;
	private static JButton add , display;
	private static JTextArea displayLabel;

	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		frame.setSize(800,800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		JPanel panel = new JPanel();
		
		frame.add(panel);
		
		panel.setLayout(null);
		
		label1 = new JLabel("Enter the ProcessID :");
		label1.setBounds(10,20,150,25);
		panel.add(label1);
		
		id = new JTextField(20);
		id.setBounds(150,20,150,25);
		panel.add(id);
		
		label2 = new JLabel("Enter the ArrivalTime:");
		label2.setBounds(10,60,150,25);
		panel.add(label2);
		
		at = new JTextField(20);
		at.setBounds(150,60,150,25);
		panel.add(at);

		label1 = new JLabel("Enter the BurstTime:");
		label1.setBounds(10,100,150,25);
		panel.add(label1);
		
		bt = new JTextField(20);
		bt.setBounds(150,100,150,25);
		panel.add(bt);
		
		
		
		add = new JButton("Add Query");
		add.setBounds(75,140,150,25);
		panel.add(add);
		
		add.addActionListener(new ScheduleProcessing());
		
		display = new JButton ("Display Schedules");
		display.setBounds(75,180,150,25);
		panel.add(display);
		
		display.addActionListener(new ScheduleProcessing());
		
		displayLabel = new JTextArea ("");
		displayLabel.setBounds(10,220,500,400);
		panel.add(displayLabel);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		
		if(e.getSource()==add) {
			int a , b , p;
			String pid =id.getText();
			p=Integer.parseInt(pid);
			
			String aid =at.getText();
			a=Integer.parseInt(aid);
			
			String bid =bt.getText();
			b=Integer.parseInt(bid);
			
			procid[n]=p;
			atime[n]=a;
			btime[n]=b;
			
			
				int temp=0;
				int temp1=0;
				int temp2=0;
				
				for( int j=0; j<(n); j++) {
					
				
				for(int i =0;i<(n);i++) {
					if(atime[i]>atime[i+1]) {
						temp=atime[i];
						atime[i]=atime[i+1];
						atime[i+1]=temp;
						
						temp1=btime[i];
						btime[i]=btime[i+1];
						btime[i+1]=temp1;
						
						temp2= procid[i];
						procid[i]= procid[i+1];
						procid[i+1]= temp2;
					}
				}
				}
				
				ct[0]=btime[0];
				
				for(int i = 1;i<n+1;i++) {
					ct[i]=ct[i-1]+btime[i];
					
					tt[i]=ct[i]-atime[i];
					wt[i]=tt[i]-btime[i];
				}
				tt[0]=ct[0]-atime[0];
			
		n++;
		System.out.println("Query Added !");
		}
		
		if(e.getSource()==display) {
			
			StringBuilder output = new StringBuilder();
			String Text;
			String Display="\n";
			
			System.out.println("FCFS \n ProcessNo  ArrivalTime  BurstTime  CompletionTime  TurnAroundTime  WaitingTime \n");
			
			Text ="FCFS \n ProcessNo  ArrivalTime  BurstTime  CompletionTime  TurnAroundTime  WaitingTime \n";
			output.append(Text);

			
			for (int i =0 ; i<n ; i++) {
				
				output.append(procid[i] + "\t" + atime[i] + "\t" + btime[i] +  "\t" + ct[i] + "\t" + tt[i] + "\t"+ wt[i] +" \n");
				
				Display =output.toString();
			
				System.out.println(procid[i] + "\t\t" + atime[i] + "\t\t" + btime[i] +  "\t \t" + ct[i] + "\t\t" + tt[i] + "\t\t"+ wt[i]);
				
			}
			
			displayLabel.setText(Display);
			System.out.println(Display);
			
		}
	}

}
