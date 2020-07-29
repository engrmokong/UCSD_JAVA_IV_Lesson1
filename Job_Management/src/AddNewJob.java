import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.EventQueue;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;

public class AddNewJob extends JFrame {

	private JPanel contentPane;
	private JTextField jobName;
	private JTextField jobSalary;
	
	ArrayList<Job> jobs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddNewJob frame = new AddNewJob();
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
	public AddNewJob() {


		setTitle("ADD NEW JOB");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 550, 328);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("CREATE A NEW JOB BY ENTERING THE DATA BELOW:");
		lblNewLabel.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 16));
		
		JLabel lblNewLabel_1 = new JLabel("NAME OF THE JOB:");
		lblNewLabel_1.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 12));
		
		JLabel lblNewLabel_2 = new JLabel("SALARY FOR THIS JOB:");
		lblNewLabel_2.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 12));
		
		jobName = new JTextField();
		jobName.setColumns(10);
		
		jobSalary = new JTextField();
		jobSalary.setColumns(10);
		
		// Save button
		JButton btnNewButton = new JButton("SAVE");
		btnNewButton.addActionListener(new ActionListener() {
		
		//Event handler for save button
		public void actionPerformed(ActionEvent e) {
		
			if(jobName.getText().isEmpty()  || jobSalary.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(null, "Please enter all fields");
			}
			
			else
			{
				String name = jobName.getText().trim();
				String salary = jobSalary.getText().trim();
				
				Job job = new Job(Double.parseDouble(salary), name); // parseDouble converts string salary to double
				
				jobs.add(job);
				
				saveJobsToFile();
				
			}
			
			
		}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(95, Short.MAX_VALUE)
					.addComponent(lblNewLabel)
					.addGap(61))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(64)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_2)
						.addComponent(lblNewLabel_1))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
							.addComponent(jobSalary)
							.addComponent(jobName, GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)))
					.addContainerGap(139, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(30)
					.addComponent(lblNewLabel)
					.addGap(56)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(jobName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(26)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(jobSalary, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addComponent(btnNewButton)
					.addContainerGap(57, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		
				//creating an instance for the ArrayList
				jobs = new ArrayList<Job>();
				populateArrayList();
	}

		
		
	private void populateArrayList() {
		// TODO Auto-generated method stub
		try
		{
			FileInputStream file = new FileInputStream("Jobs.dat"); // makes connection from the file
			ObjectInputStream inputFile = new ObjectInputStream(file); // reads from the file
			
			//to check if its the end of the file
			boolean endOfFile = false;
			
			// if its not the end of the file, it will keep extracting data
			while(!endOfFile)
			{
				try
				{
					jobs.add((Job) inputFile.readObject());
				}
				catch (EOFException e)
				{
					endOfFile = true;
				}
				catch (Exception f)
				{
					JOptionPane.showMessageDialog(null, f.getMessage());
				}
				
			}
			inputFile.close();
		}
		catch (IOException e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
	}
	
	public void saveJobsToFile()
	{
		try
		{
			FileOutputStream file = new FileOutputStream("Jobs.dat");	//connects to the file
			ObjectOutputStream outputFile = new ObjectOutputStream(file); // read from the file
			
			// writing to the file using for loop
			for(int i = 0; i < jobs.size(); i++) 
			{
				outputFile.writeObject(jobs.get(i));
				
			}
			
			//close the file
			outputFile.close();
			
			JOptionPane.showMessageDialog(null, "Successfully saved");
			this.dispose();
		}
		catch(IOException e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	
}
