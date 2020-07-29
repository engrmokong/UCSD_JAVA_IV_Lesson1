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
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"ITEM1", "ITEM 2", "ITEM 3", "ITEM 4"}));
		
		textField = new JTextField();
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"ITEM 1", "ITEM 2", "ITEM 3", "ITEM 4"}));
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		JButton btnSave = new JButton("SAVE");
		
		JButton btnDelete = new JButton("DELETE");
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
				
				populateArrayList();//
		
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
