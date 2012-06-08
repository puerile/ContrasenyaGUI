package yuri.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import yuri.PasswordGenerator;

public class GUI extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private static String NAME = "Contrasenya";

	private JLabel input = new JLabel("Length:");
	private JTextField length = new JTextField();

	private JButton start = new JButton("start");

	private JPanel c = new JPanel();
	private JScrollPane scroll = new JScrollPane(c);

	private JPanel buttons = new JPanel();

	private JTextArea textArea = new JTextArea();
	private JScrollPane area = new JScrollPane(textArea);

	private Dimension size = new Dimension(250, 45);

	// this is just for fun
	private String[] answers = {"What are you doing?",
			"You think you're being funny, don't you?",
			"Here's news for you: You aren't.", "Stop trolling me!",
			"I'm immune to trolling, you know.",
			"Actually, you're trolling yourself more than you're trolling me.",
			"You probably tell yourself this is fun.",
			"To be honest, that is kind of sad.",
			"You're not getting tired of it, are you?",
			"My grandma is a better troll than you are.",
			"In fact, everyone is.", "You know what? I'll just ignore you.",
			"..."};

	private int i = 0;

	public void setStart(boolean status)
	{
		this.start.setEnabled(status);
	}

	public GUI()
	{
		super(NAME);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(scroll);

		resetButtons();

		start.addActionListener(this);
//		DocumentListener checkLength = 
//	    length.getDocument().addDocumentListener(checkLength);

		this.initWindow();
	}

	protected void initWindow()
	{
		GridBagLayout gbl = new GridBagLayout();
		c.setLayout(gbl);

		area.setPreferredSize(size);

		buttons.add(start);

		GBL gbld = new GBL();

		gbld.addComponent(c, gbl, input, 0, 0, 1, 1, 0, 0,
				GridBagConstraints.WEST);
		gbld.addComponent(c, gbl, length, 1, 0, 1, 1, 1.0, 0,
				GridBagConstraints.EAST);
		gbld.addComponent(c, gbl, buttons, 0, 1, 4, 1, 0, 0,
				GridBagConstraints.CENTER);
		gbld.addComponent(c, gbl, area, 0, 2, 2, 1, 0, 1.0,
				GridBagConstraints.SOUTH);

		this.pack();
	}

	public void actionPerformedWithException(ActionEvent e)
			throws NoSuchAlgorithmException, IOException
	{
		if (e.getSource() == start)
		{
			start.setEnabled(false);
			PasswordGenerator pwGen = new PasswordGenerator();

			if (getLength() > 0)
			{
				textArea.setLineWrap(false);
				setText(pwGen.createPW(getLength()));
			}

			else
			{
				textArea.setLineWrap(true);
				textArea.setWrapStyleWord(true);
				setText(answers[i]);

				if (i < answers.length - 1)
				{
					i++;
				}
			}

			start.setEnabled(true);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		try
		{
			actionPerformedWithException(e);
		}

		catch (NoSuchAlgorithmException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		catch (IOException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		catch (NumberFormatException e2)
		{
			textArea.setLineWrap(true);
			textArea.setWrapStyleWord(true);
			setText(answers[i]);

			if (i < answers.length - 1)
			{
				i++;
			}
			
			start.setEnabled(true);
		}
	};

	public void resetButtons()
	{
		start.setEnabled(true);
	}

	public int getLength()
	{
		return Integer.valueOf(this.length.getText());
	}

	public void setText(String text)
	{
		this.textArea.setText(text);
	}
}
