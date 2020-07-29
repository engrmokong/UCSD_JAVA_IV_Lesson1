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

public class EditEmployee extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	
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
					EditEmployee frame = new EditEmployee();
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
	public EditEmployee() {
		setTitle("EDIT EMPLOYEE INFORMATION");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblEditEmployeeData = new JLabel("EDIT EMPLOYEE DATA:");
		lblEditEmployeeData.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 12));
		
		JLabel lblChooseEmployee = new JLabel("CHOOSE EMPLOYEE:");
		
		JLabel lblEmployeeName = new JLabel("EMPLOYEE NAME:");
		
		JLabel lblEmployeeSurname = new JLabel("EMPLOYEE SURNAME:");
		
		JLabel lblEmployeeJob = new JLabel("EMPLOYEE JOB:");
		
		JLabel lblEmployeeStaffNumber = new JLabel("EMPLOYEE STAFF NUMBER:");
		
		
		//Event handler for the comboBox
		JComboBox comboBox = new JComboBox();
		JComboBox comboBox_1 = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				int selectedIndex = comboBox.getSelectedIndex();
				
				textField.setText(employees.get(selectedIndex). getName());
				textField_1.setText(employees.get(selectedIndex).getSurname());
				textField_2.setText(employees.get(selectedIndex).getStaffNr() + "");
				
				Job job = employees.get(selectedIndex).getJob();
				
				int index = 0;
				
				for (int i = 0; i < jobs.size(); i++)
				{
					if (jobs.get(i).equals(job))
					{
						index = i;
						break;
					}
				}
				
				comboBox_1.setSelectedIndex(index);
				
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"ITEM1", "ITEM 2", "ITEM 3", "ITEM 4"}));
		
		textField = new JTextField();
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		//JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"ITEM 1", "ITEM 2", "ITEM 3", "ITEM 4"}));
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		
		//event handler for save button
		JButton btnSave = new JButton("SAVE");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if(textField.getText().isEmpty() || textField_1.getText().isEmpty() || textField_2.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Please enter all fields!");
				}
				else
				{
					
					int selectedIndex = comboBox.getSelectedIndex();
					employees.get(selectedIndex).setName(textField.getText().trim());
					employees.get(selectedIndex).setSurname(textField_1.getText().trim());
					employees.get(selectedIndex).setStaffNr(Integer.parseInt(textField_2.getText().trim()));
					
					Job job  = jobs.get(comboBox_1.getSelectedIndex());
					employees.get(selectedIndex).setJob(job);
					
					/////////save to file /////////////
					saveEmployeesToFile();



										
				}
			}
		});
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			
			///////event handler for delete button ///////////
			public void actionPerformed(ActionEvent e)
			{
				int selectedIndex = comboBox.getSelectedIndex();
				
				employees.remove(selectedIndex);
				saveEmployeesToFileDelete();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(141)
							.addComponent(lblEditEmployeeData))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(30)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblEmployeeName)
								.addComponent(lblChooseEmployee)
								.addComponent(lblEmployeeSurname)
								.addComponent(lblEmployeeJob)
								.addComponent(lblEmployeeStaffNumber))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(textField_2)
								.addComponent(comboBox_1, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(textField_1)
								.addComponent(textField)
								.addComponent(comboBox, 0, 163, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnSave)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnDelete)))))
					.addContainerGap(88, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(19)
					.addComponent(lblEditEmployeeData)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblChooseEmployee)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmployeeName)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmployeeSurname)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmployeeJob)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmployeeStaffNumber)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSave)
						.addComponent(btnDelete))
					.addContainerGap(35, Short.MAX_VALUE))
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
						comboBox_1.setModel(new DefaultComboBoxModel(jobsArray));
						
				String[] empArray = new String [employees.size()];

				for(int i = 0; i < employees.size(); i++)
				{
					//jobsArray [i] = jobs.get(i).getNameOfJob() + ", R" + formatter.format(jobs.get(i).getSalary());
					empArray[i] = employees.get(i).getName() + ", " + employees.get(i).getSurname();	
					
				}
				////comboBox.setModel(new DefaultComboBoxModel(new String[] {"ITEM 1", "ITEM 2", "ITEM 3", "ITEM 4"}));
				comboBox.setModel(new DefaultComboBoxModel(empArray));
				
				
				comboBox.setSelectedIndex(0);
		
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
	
	public void saveEmployeesToFileDelete()
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
			
			JOptionPane.showMessageDialog(null, "Successfully deleted");
			this.dispose();
		}
		catch(IOException e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
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

}
