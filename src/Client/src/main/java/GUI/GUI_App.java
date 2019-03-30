package GUI;

import entity.LeaderBoardEntry;
import entity.User;
import helper.PointsMissing;
import helper.UpdatingLabels;
import service.UserServiceImpl;

import javax.swing.*;
import javax.swing.border.Border;

import GUI_mainscreen.AddFriend;
import GUI_mainscreen.FriendsProfile;
import GUI_mainscreen.MyProfile;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * The type Gui app.
 */
public class GUI_App extends Login_S {

	private static JFrame frame;
	private static String username;
	private static int points;
	private static int level;
	private static int pointsmissing;


	/**
     * Launch the application.
     *
     * @param username the username
     * @param userIn   the user in
     * @param clientIn the client in
     */
    public static void application(String username, User userIn, UserServiceImpl clientIn) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_App window = new GUI_App(username, userIn, clientIn);
					GUI_App.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	private GUI_App(String username, User userIn, UserServiceImpl clientIn) {
		this.setUsername(username);
		initialize(username, userIn, clientIn);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private static void initialize(String username, User userIn, UserServiceImpl clientIn) {
		// Create the frame
		frame = new JFrame();
		frame.setBackground(new Color(0, 255, 127));
		frame.getContentPane().setBackground(SystemColor.inactiveCaptionBorder);
		frame.setBounds(200, 200, 1920, 1080);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setFocusable(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.getContentPane().setLayout(null);

		// Labels
		JLabel lblWelcome = new JLabel("Welcome " + username);
		lblWelcome.setFont(new Font("Times New Roman", Font.PLAIN, 29));
		lblWelcome.setBounds(10, 10, 1520, 59);
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblWelcome);

		points = userIn.getProduce() + userIn.getSolar() + userIn.getTemperature() + userIn.getTransportation() + userIn.getVegetarian();

		//Information about CO2 saved
		JLabel lblPoints = new JLabel("The amount of CO2 you have saved so far is " + points);
		lblPoints.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPoints.setBounds(10, 42, 1520, 50);
		lblPoints.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblPoints);

		// Information about level
		level = points / 1000;
		JLabel lblLevel = new JLabel("Your Level is " + level);
		lblLevel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLevel.setBounds(10, 74, 1520, 50);
		lblLevel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblLevel);

		// Leaderboard
		JLabel lblLeaderboard = new JLabel("Leaderboard Top 10");
		lblLeaderboard.setFont(new Font("Arial Black", Font.PLAIN, 13));
		lblLeaderboard.setBounds(1250, 140, 260, 30);
		lblLeaderboard.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblLeaderboard);

		// Create the content for the leaderboard
		String LeaderBoardContent = LeaderBoardEntry.createLeaderboard(points, username, userIn);

		JLabel lblLeaderboardContent = new JLabel(LeaderBoardContent);
		lblLeaderboardContent.setBackground(Color.WHITE);
		lblLeaderboardContent.setBounds(1250, 170, 260, 200);
		lblLeaderboardContent.setHorizontalAlignment(SwingConstants.CENTER);
		Border border = BorderFactory.createLineBorder(Color.GRAY, 2);
		lblLeaderboardContent.setBorder(border);
		frame.getContentPane().add(lblLeaderboardContent);

		// Add friend button
		JButton btnAddFriend = new JButton("Add a friend");
		btnAddFriend.setForeground(Color.WHITE);
		btnAddFriend.setBackground(new Color(0, 255, 127));
		btnAddFriend.setFont(new Font("Arial Black", Font.PLAIN, 13));
		btnAddFriend.setBounds(1250, 388, 260, 40);
		btnAddFriend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddFriend.application(user, client);
			}
		});
		frame.getContentPane().add(btnAddFriend);

		// Motivation

		if (points > 0) {
			pointsmissing = 1000 % points;
		}
		String motivation = "You only need " + pointsmissing + " points to the next level!";
		JLabel lblMotivation = new JLabel(motivation);
		lblMotivation.setFont(new Font("Arial Black", Font.PLAIN, 13));
		lblMotivation.setBounds(193, 472, 462, 30);
		lblMotivation.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblMotivation);


		// Vegetarian
		//Labels
		JLabel lblWhatKindOf = new JLabel("What kind of meal did you have today?");
		lblWhatKindOf.setFont(new Font("Arial Black", Font.PLAIN, 14));
		lblWhatKindOf.setBounds(52, 140, 333, 30);
		lblWhatKindOf.setHorizontalAlignment(SwingConstants.LEFT);
		frame.getContentPane().add(lblWhatKindOf);

		// Vegetarian meal
		JButton btnVeg = new JButton("Vegetarian meal");
		btnVeg.setForeground(Color.WHITE);
		btnVeg.setFont(new Font("Arial Black", Font.PLAIN, 13));
		btnVeg.setBackground(new Color(0, 255, 127));
		btnVeg.setBounds(52, 170, 260, 40);
		btnVeg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UpdatingLabels.updateEverything(userIn, clientIn, lblPoints, lblLeaderboardContent, lblMotivation, lblLevel, "Vegetarian");
			}
		});
		frame.getContentPane().add(btnVeg);

		JButton btnNonVeg = new JButton("Carnivore meal");
		btnNonVeg.setBackground(new Color(102, 204, 153));
		btnNonVeg.setForeground(Color.WHITE);
		btnNonVeg.setFont(new Font("Arial Black", Font.PLAIN, 13));
		btnNonVeg.setBounds(52, 225, 260, 40);
		btnNonVeg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UpdatingLabels.updateEverything(userIn, clientIn, lblPoints, lblLeaderboardContent, lblMotivation, lblLevel, "Carnivore");
			}
		});
		frame.getContentPane().add(btnNonVeg);


		// Produce

		//Labels
		JLabel lblProduce = new JLabel("What type of produce did you buy today?");
		lblProduce.setFont(new Font("Arial Black", Font.PLAIN, 13));
		lblProduce.setBounds(52, 290, 333, 30);
		lblProduce.setHorizontalAlignment(SwingConstants.LEFT);
		frame.getContentPane().add(lblProduce);

		JButton btnLocalProduce = new JButton("Local Produce");
		btnLocalProduce.setForeground(Color.WHITE);
		btnLocalProduce.setBackground(new Color(0, 255, 127));
		btnLocalProduce.setFont(new Font("Arial Black", Font.PLAIN, 13));
		btnLocalProduce.setBounds(52, 319, 260, 40);
		btnLocalProduce.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UpdatingLabels.updateEverything(userIn, clientIn, lblPoints, lblLeaderboardContent, lblMotivation, lblLevel, "LocalProduce");
			}
		});
		frame.getContentPane().add(btnLocalProduce);

		JButton btnGlobalProduce = new JButton("Global Produce");
		btnGlobalProduce.setForeground(Color.WHITE);
		btnGlobalProduce.setFont(new Font("Arial Black", Font.PLAIN, 13));
		btnGlobalProduce.setBackground(new Color(102, 204, 153));
		btnGlobalProduce.setBounds(52, 372, 260, 40);
		btnGlobalProduce.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UpdatingLabels.updateEverything(userIn, clientIn, lblPoints, lblLeaderboardContent, lblMotivation, lblLevel, "GlobalProduce");
			}
		});
		frame.getContentPane().add(btnGlobalProduce);


		// Transportation

		//Labels
		JLabel lblTransportation = new JLabel("Did you use a bike instead of a car today?");
		lblTransportation.setFont(new Font("Arial Black", Font.PLAIN, 13));
		lblTransportation.setBounds(445, 137, 369, 30);
		lblTransportation.setHorizontalAlignment(SwingConstants.LEFT);
		frame.getContentPane().add(lblTransportation);

		JButton btnBike = new JButton("Yes");
		btnBike.setForeground(Color.WHITE);
		btnBike.setFont(new Font("Arial Black", Font.PLAIN, 13));
		btnBike.setBackground(new Color(0, 255, 127));
		btnBike.setBounds(445, 170, 260, 40);
		btnBike.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		frame.getContentPane().add(btnBike);

		JButton btnCar = new JButton("No");
		btnCar.setForeground(Color.WHITE);
		btnCar.setFont(new Font("Arial Black", Font.PLAIN, 13));
		btnCar.setBackground(new Color(102, 204, 153));
		btnCar.setBounds(445, 225, 260, 40);
		btnCar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		frame.getContentPane().add(btnCar);

		// Temperature

		//Labels
		JLabel lblTemperature = new JLabel("Have you lowered your room temperature today?");
		lblTemperature.setFont(new Font("Arial Black", Font.PLAIN, 13));
		lblTemperature.setBounds(445, 290, 388, 30);
		lblTemperature.setHorizontalAlignment(SwingConstants.LEFT);
		frame.getContentPane().add(lblTemperature);

		JButton btnLowered = new JButton("Yes");
		btnLowered.setForeground(Color.WHITE);
		btnLowered.setBackground(new Color(0, 255, 127));
		btnLowered.setFont(new Font("Arial Black", Font.PLAIN, 13));
		btnLowered.setBounds(445, 319, 260, 40);
		btnLowered.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		frame.getContentPane().add(btnLowered);

		JButton btnUnchanged = new JButton("No");
		btnUnchanged.setForeground(Color.WHITE);
		btnUnchanged.setFont(new Font("Arial Black", Font.PLAIN, 13));
		btnUnchanged.setBackground(new Color(102, 204, 153));
		btnUnchanged.setBounds(445, 372, 260, 40);
		btnUnchanged.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		frame.getContentPane().add(btnUnchanged);

		// Solar

		//Labels
		JLabel lblSolar = new JLabel("Have you installed solar panels today?");
		lblSolar.setFont(new Font("Arial Black", Font.PLAIN, 13));
		lblSolar.setBounds(845, 290, 354, 30);
		lblSolar.setHorizontalAlignment(SwingConstants.LEFT);
		frame.getContentPane().add(lblSolar);

		JButton btnSolar = new JButton("Yes");
		btnSolar.setForeground(Color.WHITE);
		btnSolar.setBackground(new Color(0, 255, 127));
		btnSolar.setFont(new Font("Arial Black", Font.PLAIN, 13));
		btnSolar.setBounds(845, 319, 260, 40);
		btnSolar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		frame.getContentPane().add(btnSolar);

		JButton btnNoSolar = new JButton("No");
		btnNoSolar.setForeground(Color.WHITE);
		btnNoSolar.setFont(new Font("Arial Black", Font.PLAIN, 13));
		btnNoSolar.setBackground(new Color(102, 204, 153));
		btnNoSolar.setBounds(845, 372, 260, 40);
		btnNoSolar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		frame.getContentPane().add(btnNoSolar);

		JLabel lblDidYouUse = new JLabel("Did you use public transport instead of a car today?");
		lblDidYouUse.setHorizontalAlignment(SwingConstants.LEFT);
		lblDidYouUse.setFont(new Font("Arial Black", Font.PLAIN, 13));
		lblDidYouUse.setBounds(845, 140, 420, 30);
		frame.getContentPane().add(lblDidYouUse);

		JButton btnPublicTransportYes = new JButton("Yes");
		btnPublicTransportYes.setForeground(Color.WHITE);
		btnPublicTransportYes.setFont(new Font("Arial Black", Font.PLAIN, 13));
		btnPublicTransportYes.setBackground(new Color(0, 255, 127));
		btnPublicTransportYes.setBounds(845, 170, 260, 40);
		btnPublicTransportYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		frame.getContentPane().add(btnPublicTransportYes);

		JButton btnPublicTransportNo = new JButton("No");
		btnPublicTransportNo.setForeground(Color.WHITE);
		btnPublicTransportNo.setFont(new Font("Arial Black", Font.PLAIN, 13));
		btnPublicTransportNo.setBackground(new Color(102, 204, 153));
		btnPublicTransportNo.setBounds(845, 223, 260, 40);
		btnPublicTransportNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		frame.getContentPane().add(btnPublicTransportNo);


		// Creates the menu-bar
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);


		// Creates the "Log out" in menu-bar
		JMenuItem mntmLogOut = new JMenuItem("Log out");
		mntmLogOut.setForeground(Color.DARK_GRAY);
		mntmLogOut.setFont(new Font("Arial Black", Font.PLAIN, 13));
		mntmLogOut.setBackground(new Color(204, 255, 255));
		mntmLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (JOptionPane.showConfirmDialog(frame, "Confirm if you want to exit", "Log out",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
					Login_S.main(null);
					frame.setVisible(false);
				}
			}
		});

		// Creates the "My Profile" in menu-bar
		JMenuItem mntmMyProfile = new JMenuItem("My Profile");
		mntmMyProfile.setForeground(Color.DARK_GRAY);
		mntmMyProfile.setBackground(new Color(0, 204, 204));
		mntmMyProfile.setFont(new Font("Arial Black", Font.PLAIN, 13));
		mntmMyProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MyProfile.application(userIn, clientIn, username, friendClient, frame);
				setUsername(userIn.getUser_name());
				lblWelcome.setText("Welcome " + userIn.getUser_name());
				System.out.println(userIn.getUser_name());
			}
		});
		menuBar.add(mntmMyProfile);

		// Creates the "My Friends" in menu-bar
		JMenuItem mntmMyFriends = new JMenuItem("My Friends");
		mntmMyFriends.setForeground(Color.DARK_GRAY);
		mntmMyFriends.setBackground(new Color(0, 255, 204));
		mntmMyFriends.setFont(new Font("Arial Black", Font.PLAIN, 13));
		mntmMyFriends.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FriendsProfile.application(userIn, clientIn);
			}
		});
		menuBar.add(mntmMyFriends);
		menuBar.add(mntmLogOut);


	}


	/**
	 * Gets username.
	 *
	 * @return the username
	 */
//Getters and Setters username
	public static String getUsername() {
		return username;
	}

	/**
	 * Sets username.
	 *
	 * @param usernameIn the username
	 */
	public static void setUsername(String usernameIn) {
		username = usernameIn;
	}

	public static int getPoints() {
		return points;
	}

	public static void setPoints(int points) {
		GUI_App.points = points;
	}

	public static int getLevel() {
		return level;
	}

	public static void setLevel(int level) {
		GUI_App.level = level;
	}

	public static int getPointsmissing() {
		return pointsmissing;
	}

	public static void setPointsmissing(int pointsmissing) {
		GUI_App.pointsmissing = pointsmissing;
	}
}
