import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout.Alignment;

import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.*;
import java.util.*;
import java.io.*;
import java.text.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class AddEmployee extends JFrame {

	private JPanel contentPane;
	private JTextField textField1;
	private JTextField textField2;
	private JTextField textField3;
	
	//adding an arrayList
	ArrayList<Job> jobs;
	ArrayList<Employee> employees;
	DecimalFormat formatter;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddEmployee frame = new AddEmployee();
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
	public AddEmployee() {
		setAlwaysOnTop(true);
		setTitle("ADD NEW EMPLOYEE");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 595, 305);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("ADD A NEW EMPLOYEEE INFORAMTION:");
		lblNewLabel.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 12));
		
		JLabel lblNewLabel_1 = new JLabel("ENTER EMPLOYEE NAME:");
		lblNewLabel_1.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 11));
		
		JLabel lblEnterEmployeeSurname = new JLabel("ENTER EMPLOYEE SURNAME:");
		
		JLabel lblChooseAJob = new JLabel("CHOOSE A JOB:");
		
		JLabel lblEnterTheStaff = new JLabel("ENTER THE STAFF NUMBER:");
		
		textField1 = new JTextField();
		textField1.setColumns(10);
		
		textField2 = new JTextField();
		textField2.setColumns(10);
		
		textField3 = new JTextField();
		textField3.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"ITEM 1", "ITEM 2", "ITEM 3", "ITEM 4"}));
		
		JButton btnSave = new JButton("SAVE");
		btnSave.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				if(textField1.getText().isEmpty() || textField2.getText().isEmpty() || textField3.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Please enter all fields!");
				}
				else
				{
					
					String name = textField1.getText().trim();
					String surname = textField2.getText().trim();
					int jobIndex = comboBox.getSelectedIndex();
					Job job  = jobs.get(jobIndex);
					int staffNr = Integer.parseInt(textField3.getText().trim());
					
					Employee employee = new Employee(name, surname, job,  staffNr);
					
					//adding on arrayList
					employees.add(employee);
					
					saveEmployeesToFile();//
										
				}
				
				
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(116)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblEnterEmployeeSurname)
								.addComponent(lblNewLabel_1)
								.addComponent(lblChooseAJob)
								.addComponent(lblEnterTheStaff))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(textField3)
								.addComponent(textField2)
								.addComponent(comboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(textField1, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(247)
							.addComponent(btnSave))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(175)
							.addComponent(lblNewLabel)))
					.addContainerGap(107, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(21)
					.addComponent(lblNewLabel)
					.addGap(29)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(textField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEnterEmployeeSurname)
						.addComponent(textField2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblChooseAJob)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEnterTheStaff)
						.addComponent(textField3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
					.addComponent(btnSave)
					.addGap(26))
		);
		contentPane.setLayout(gl_contentPane);
		
		//initiating the arrayList for Jobs and employees
		formatter = new DecimalFormat("#,###.00");
		
		jobs = new ArrayList<Job>();
		employees = new ArrayList <Employee>();
		
		populateArrayList();
		
		String[] jobsArray = new String [jobs.size()];
		
		for(int i = 0; i < jobs.size(); i++)
		{
			jobsArray [i] = jobs.get(i).getNameOfJob() + ", R" + formatter.format(jobs.get(i).getSalary());
		}
				////comboBox.setModel(new DefaultComboBoxModel(new String[] {"ITEM 1", "ITEM 2", "ITEM 3", "ITEM 4"}));
				comboBox.setModel(new DefaultComboBoxModel(jobsArray));
		
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
		
		try
		{
			FileInputStream file2 = new FileInputStream("Employees.dat"); // makes connection from the file
			ObjectInputStream inputFile2 = new ObjectInputStream(file2); // reads from the file
			
			//to check if its the end of the file
			boolean endOfFile = false;
			
			// if its not the end of the file, it will keep extracting data
			while(!endOfFile)
			{
				try
				{
					employees.add((Employee) inputFile2.readObject());
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
			inputFile2.close();
		}
		catch (IOException e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
	}
	
	public void saveEmployeesToFile()
	{
		try
		{
			FileOutputStream file2 = new FileOutputStream("Employees.dat");	//connects to the file
			ObjectOutputStream outputFile = new ObjectOutputStream(file2); // read from the file
			
			// writing to the file using for loop
			for(int i = 0; i < employees.size(); i++) 
			{
				outputFile.writeObject(employees.get(i));
				
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
