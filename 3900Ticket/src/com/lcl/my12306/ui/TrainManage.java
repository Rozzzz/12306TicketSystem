package com.lcl.my12306.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Combo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.wb.swt.SWTResourceManager;

import com.lcl.my12306.dao.DBHelper;
import com.lcl.my12306.dao.StationinfoDBH;
import com.lcl.my12306.dao.TrainDBH;
import com.lcl.my12306.dao.TraininfoDBH;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.nebula.widgets.calendarcombo.CalendarCombo;

public class TrainManage {

	protected Shell shell;
	private Display display;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Text text_5;
	private Text text_6;
	private Text text_7;
	private Text text_8;
	private Text text_9;
	private Text text_10;
	private Text text_11;
	private Text text_12;
	private Text text_13;
	private Text text_14;
	private Text text_15;
	private Text text_16;
	private Text text_17;
	private Text text_18;
	private Text text_19;
	private Text text_20;
	private Text text_21;
	private Text text_22;
	private Text text_23;
	private Table table;
	private Text text_24;
	protected Shell parent;
	private boolean[] bl=new boolean[5];
	private boolean bll;
	private Button button;
	private boolean[] blll=new boolean[2];
	private Text text_25;
	private Text text_26;
	private Text text_27;
	private Text text_28;
	private Text text_29;
	private Text text_30;
	private Text text_31;
	private Text text_32;
	private Text text_33;
	private Text text_34;
	private Text text_35;
	static Text text;
	static String iii="";


	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			TrainManage window = new TrainManage();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setImage(SWTResourceManager.getImage(TrainManage.class, "/image/12306_logo.jpg"));
		shell.setText("列车管理");
		shell.setSize(700, 431);
		shell.setBounds(0, 0, display.getClientArea().width,display.getClientArea().height);
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));

		TabFolder tabFolder = new TabFolder(shell, SWT.NONE);

		TabItem tbtmNewItem = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem.setText("添加站点信息");

		SashForm sashForm = new SashForm(tabFolder, SWT.NONE);
		sashForm.setOrientation(SWT.VERTICAL);
		tbtmNewItem.setControl(sashForm);

		Composite composite_2 = new Composite(sashForm, SWT.NONE);
		composite_2.setLayout(new FillLayout(SWT.HORIZONTAL));

		table = new Table(composite_2, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);

		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(198);
		tableColumn.setText("站点编号");

		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(222);
		tableColumn_1.setText("站点名称");

		Menu menu = new Menu(table);
		table.setMenu(menu);

		MenuItem menuItem = new MenuItem(menu, SWT.NONE);
		menuItem.setText("删除");

		Composite composite_3 = new Composite(sashForm, SWT.NONE);
		composite_3.setBackgroundImage(SWTResourceManager.getImage(TrainManage.class, "/image/b13.jpg"));
		composite_3.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));

		Label label_19 = new Label(composite_3, SWT.NONE);
		label_19.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_19.setText("站点名");
		label_19.setBounds(109, 58, 61, 23);

		text_24 = new Text(composite_3, SWT.BORDER);
		text_24.setMessage("请输入中文");
		text_24.setBounds(185, 59, 113, 23);

		Button button_3 = new Button(composite_3, SWT.NONE);
		button_3.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		button_3.setText("添加");
		button_3.setBounds(484, 53, 80, 27);

		Label label_21 = new Label(composite_3, SWT.NONE);
		label_21.setText("!");
		label_21.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_21.setBounds(320, 62, 139, 17);

		Label label_20 = new Label(composite_3, SWT.NONE);

		label_20.setAlignment(SWT.CENTER);
		label_20.setBackground(SWTResourceManager.getColor(245, 222, 179));
		label_20.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_20.setText("查看列车运营详情");
		label_20.setBounds(636, 58, 158, 23);
		
		text = new Text(composite_3, SWT.BORDER);
		text.setVisible(false);
		text.setBounds(0, 0, 73, 23);
		sashForm.setWeights(new int[] {520, 137});

		TabItem tbtmNewItem_1 = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem_1.setText("添加运行的车次");

		Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		composite_1.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite_1.setBackgroundImage(SWTResourceManager.getImage(TrainManage.class, "/image/b9.jpg"));
		tbtmNewItem_1.setControl(composite_1);

		Button button_2 = new Button(composite_1, SWT.NONE);
		button_2.setText("添加");
		button_2.setFont(SWTResourceManager.getFont("微软雅黑", 13, SWT.NORMAL));
		button_2.setBounds(578, 276, 88, 37);

		Label label_13 = new Label(composite_1, SWT.NONE);
		label_13.setText("添加车次");
		label_13.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_13.setBounds(187, 61, 88, 25);

		Label label_14 = new Label(composite_1, SWT.NONE);
		label_14.setText("运营开始");
		label_14.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_14.setBounds(359, 61, 88, 25);

		Label label_15 = new Label(composite_1, SWT.NONE);
		label_15.setText("运营结束");
		label_15.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_15.setBounds(536, 61, 88, 25);

		Label label_16 = new Label(composite_1, SWT.NONE);
		label_16.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_16.setBounds(169, 396, 176, 25);
		label_16.setText("取消未支付订单的时间");

		text_23 = new Text(composite_1, SWT.BORDER);
		text_23.setBounds(396, 398, 73, 23);

		Label lblNewLabel = new Label(composite_1, SWT.NONE);
		lblNewLabel.setBounds(499, 404, 61, 17);
		lblNewLabel.setText("单位：分钟");

		text_35 = new Text(composite_1, SWT.BORDER);
		text_35.setBounds(169, 113, 106, 27);

		final CalendarCombo calendarCombo = new CalendarCombo(composite_1, SWT.NONE);
		calendarCombo.setBounds(345, 113, 102, 27);

		final CalendarCombo calendarCombo_1 = new CalendarCombo(composite_1, SWT.NONE);
		calendarCombo_1.setBounds(522, 113, 102, 25);

		TabItem tabItem_2 = new TabItem(tabFolder, SWT.NONE);
		tabItem_2.setText("添加列车信息");

		Composite composite = new Composite(tabFolder, SWT.NONE);
		tabItem_2.setControl(composite);
		composite.setBackgroundImage(SWTResourceManager.getImage(TrainManage.class, "/image/b9.jpg"));
		composite.setFont(SWTResourceManager.getFont("楷体", 14, SWT.NORMAL));
		composite.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));

		Label label = new Label(composite, SWT.NONE);
		label.setText("车次：");
		label.setFont(SWTResourceManager.getFont("楷体", 23, SWT.NORMAL));
		label.setBounds(73, 403, 88, 37);

		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setText("软卧总数：");
		label_1.setFont(SWTResourceManager.getFont("黑体", 15, SWT.NORMAL));
		label_1.setBounds(60, 309, 88, 23);

		Label label_2 = new Label(composite, SWT.NONE);
		label_2.setText("硬卧总数：");
		label_2.setFont(SWTResourceManager.getFont("黑体", 15, SWT.NORMAL));
		label_2.setBounds(522, 307, 96, 25);

		Label label_3 = new Label(composite, SWT.NONE);
		label_3.setText("硬座总数：");
		label_3.setFont(SWTResourceManager.getFont("微软雅黑", 15, SWT.NORMAL));
		label_3.setBounds(291, 303, 92, 31);

		Label label_4 = new Label(composite, SWT.NONE);
		label_4.setText("起始站");
		label_4.setFont(SWTResourceManager.getFont("黑体", 13, SWT.NORMAL));
		label_4.setBounds(60, 12, 66, 23);

		Label label_5 = new Label(composite, SWT.NONE);
		label_5.setText("\u7EC8\u70B9\u7AD9");
		label_5.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_5.setBounds(194, 10, 66, 23);

		Label label_6 = new Label(composite, SWT.NONE);
		label_6.setText("软卧票价");
		label_6.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_6.setBounds(520, 10, 66, 25);

		Label label_7 = new Label(composite, SWT.NONE);
		label_7.setText("硬卧票价");
		label_7.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_7.setBounds(617, 10, 66, 25);

		Label label_8 = new Label(composite, SWT.NONE);
		label_8.setText("硬座票价");
		label_8.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_8.setBounds(739, 10, 66, 23);

		text_1 = new Text(composite, SWT.BORDER);
		text_1.setText("无");
		text_1.setBounds(405, 309, 73, 23);

		text_2 = new Text(composite, SWT.BORDER);
		text_2.setText("无");
		text_2.setBounds(624, 308, 73, 23);

		text_3 = new Text(composite, SWT.BORDER);
		text_3.setText("无");
		text_3.setBounds(167, 309, 73, 25);

		final Combo combo = new Combo(composite, SWT.READ_ONLY);
		combo.setBounds(38, 54, 88, 25);
		combo.setText("无");

		final Combo combo_1 = new Combo(composite, SWT.READ_ONLY);
		combo_1.setBounds(175, 54, 88, 25);
		combo_1.setText("无");

		final Combo combo_2 = new Combo(composite, SWT.READ_ONLY);
		combo_2.setBounds(175, 96, 88, 25);
		combo_2.setText("无");

		final Combo combo_3 = new Combo(composite, SWT.READ_ONLY);
		combo_3.setBounds(175, 136, 88, 25);
		combo_3.setText("无");

		final Combo combo_4 = new Combo(composite, SWT.READ_ONLY);
		combo_4.setBounds(175, 180, 88, 25);
		combo_4.setText("无");

		text_4 = new Text(composite, SWT.BORDER);
		text_4.setEditable(false);
		text_4.setBounds(38, 96, 88, 23);

		text_5 = new Text(composite, SWT.BORDER);
		text_5.setEditable(false);
		text_5.setBounds(38, 136, 88, 23);

		text_6 = new Text(composite, SWT.BORDER);
		text_6.setEditable(false);
		text_6.setBounds(38, 180, 88, 23);

		final Combo combo_5 = new Combo(composite, SWT.READ_ONLY);
		combo_5.setBounds(175, 220, 88, 25);
		combo_5.setText("无");

		text_7 = new Text(composite, SWT.BORDER);
		text_7.setEditable(false);
		text_7.setBounds(38, 220, 88, 23);

		text_8 = new Text(composite, SWT.BORDER);
		text_8.setText("0");
		text_8.setEditable(false);
		text_8.setBounds(513, 54, 73, 23);

		text_9 = new Text(composite, SWT.BORDER);
		text_9.setText("0");
		text_9.setEditable(false);
		text_9.setBounds(513, 96, 73, 23);

		text_10 = new Text(composite, SWT.BORDER);
		text_10.setText("0");
		text_10.setEditable(false);
		text_10.setBounds(513, 136, 73, 23);

		text_11 = new Text(composite, SWT.BORDER);
		text_11.setText("0");
		text_11.setEditable(false);
		text_11.setBounds(513, 180, 73, 23);

		text_12 = new Text(composite, SWT.BORDER);
		text_12.setText("0");
		text_12.setEditable(false);
		text_12.setBounds(513, 220, 73, 23);

		text_13 = new Text(composite, SWT.BORDER);
		text_13.setText("0");
		text_13.setEditable(false);
		text_13.setBounds(624, 54, 73, 23);

		text_14 = new Text(composite, SWT.BORDER);
		text_14.setText("0");
		text_14.setEditable(false);
		text_14.setBounds(624, 96, 73, 23);

		text_15 = new Text(composite, SWT.BORDER);
		text_15.setText("0");
		text_15.setEditable(false);
		text_15.setBounds(624, 136, 73, 23);

		text_16 = new Text(composite, SWT.BORDER);
		text_16.setText("0");
		text_16.setEditable(false);
		text_16.setBounds(624, 180, 73, 23);

		text_17 = new Text(composite, SWT.BORDER);
		text_17.setText("0");
		text_17.setEditable(false);
		text_17.setBounds(624, 220, 73, 23);

		text_18 = new Text(composite, SWT.BORDER);
		text_18.setText("0");
		text_18.setEditable(false);
		text_18.setBounds(732, 54, 73, 23);

		text_19 = new Text(composite, SWT.BORDER);
		text_19.setText("0");
		text_19.setEditable(false);
		text_19.setBounds(732, 96, 73, 23);

		text_20 = new Text(composite, SWT.BORDER);
		text_20.setText("0");
		text_20.setEditable(false);
		text_20.setBounds(732, 136, 73, 23);

		text_21 = new Text(composite, SWT.BORDER);
		text_21.setText("0");
		text_21.setEditable(false);
		text_21.setBounds(732, 180, 73, 23);

		text_22 = new Text(composite, SWT.BORDER);
		text_22.setText("0");
		text_22.setEditable(false);
		text_22.setBounds(732, 220, 73, 23);

		Label label_9 = new Label(composite, SWT.NONE);
		label_9.setText("发车时间");
		label_9.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_9.setBounds(317, 10, 66, 25);

		Label label_10 = new Label(composite, SWT.NONE);
		label_10.setText("到站时间");
		label_10.setFont(SWTResourceManager.getFont("微软雅黑", 13, SWT.NORMAL));
		label_10.setBounds(405, 9, 73, 23);

		button = new Button(composite, SWT.NONE);
		button.setText("提  交");
		button.setBounds(454, 403, 96, 37);
		button.setEnabled(false);

		Label label_11 = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_11.setBounds(0, 460, 1342, 8);

		Label label_12 = new Label(composite, SWT.NONE);
		label_12.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_12.setBounds(85, 486, 61, 37);
		label_12.setText("预  览");

		final Button button_1 = new Button(composite, SWT.NONE);
		button_1.setBounds(797, 581, 80, 27);
		button_1.setText("确认提交");
		button_1.setEnabled(false);

		Label label_18 = new Label(composite, SWT.NONE);

		label_18.setBackground(SWTResourceManager.getColor(245, 222, 179));
		label_18.setAlignment(SWT.CENTER);
		label_18.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_18.setBounds(926, 582, 152, 23);
		label_18.setText("查看列车运营详情");

		Label label_22 = new Label(composite, SWT.NONE);
		label_22.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_22.setBounds(264, 52, 47, 25);

		Label label_23 = new Label(composite, SWT.NONE);
		label_23.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_23.setBounds(264, 96, 47, 25);

		Label label_24 = new Label(composite, SWT.NONE);
		label_24.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_24.setBounds(264, 136, 47, 25);

		Label label_25 = new Label(composite, SWT.NONE);
		label_25.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_25.setBounds(264, 180, 47, 25);

		Label label_26 = new Label(composite, SWT.NONE);
		label_26.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_26.setBounds(264, 220, 47, 25);

		final Label label_27 = new Label(composite, SWT.NONE);
		label_27.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_27.setBounds(460, 57, 47, 17);

		final Label label_28 = new Label(composite, SWT.NONE);
		label_28.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_28.setBounds(460, 99, 47, 17);

		final Label label_29 = new Label(composite, SWT.NONE);
		label_29.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_29.setBounds(460, 139, 47, 17);

		final Label label_30 = new Label(composite, SWT.NONE);
		label_30.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_30.setBounds(460, 183, 47, 17);

		final Label label_31 = new Label(composite, SWT.NONE);
		label_31.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_31.setBounds(460, 223, 47, 17);

		final Label label_32 = new Label(composite, SWT.NONE);
		label_32.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_32.setBounds(177, 337, 47, 17);

		final Label label_33 = new Label(composite, SWT.NONE);
		label_33.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_33.setBounds(415, 337, 47, 17);

		final Label label_34 = new Label(composite, SWT.NONE);
		label_34.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_34.setBounds(634, 337, 47, 17);

		text_25 = new Text(composite, SWT.BORDER);
		text_25.setEditable(false);
		text_25.setBounds(317, 54, 61, 23);

		text_26 = new Text(composite, SWT.BORDER);
		text_26.setEditable(false);
		text_26.setBounds(401, 54, 61, 23);

		text_27 = new Text(composite, SWT.BORDER);
		text_27.setEditable(false);
		text_27.setBounds(317, 98, 61, 23);

		text_28 = new Text(composite, SWT.BORDER);
		text_28.setEditable(false);
		text_28.setBounds(401, 96, 61, 23);




		text_29 = new Text(composite, SWT.BORDER);
		text_29.setEditable(false);
		text_29.setBounds(317, 138, 61, 23);

		text_30 = new Text(composite, SWT.BORDER);
		text_30.setEditable(false);
		text_30.setBounds(401, 136, 61, 23);

		text_31 = new Text(composite, SWT.BORDER);
		text_31.setEditable(false);
		text_31.setBounds(317, 182, 61, 23);

		text_32 = new Text(composite, SWT.BORDER);
		text_32.setEditable(false);
		text_32.setBounds(401, 182, 61, 23);

		text_33 = new Text(composite, SWT.BORDER);
		text_33.setEditable(false);
		text_33.setBounds(317, 222, 61, 23);

		text_34 = new Text(composite, SWT.BORDER);
		text_34.setEditable(false);
		text_34.setBounds(401, 220, 61, 23);

		final Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setBounds(100, 538, 619, 70);

		final Combo combo_6 = new Combo(composite, SWT.NONE);
		combo_6.setBounds(175, 404, 96, 36);

		final TrainQuery trainQuery=new TrainQuery();
		label_18.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				shell.close();
				trainQuery.open();
			}
		});

		//点击查看列车运营详情
		label_20.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				shell.close();
				trainQuery.open();
			}
		});
		//点击添加 添加站点
		button_3.addSelectionListener(new SelectionAdapter() {
			StationinfoDBH stationinfoDBH=new StationinfoDBH();
			public void widgetSelected(SelectionEvent e) {
				String s_name=text_24.getText().trim();
				if(stationinfoDBH.add(s_name)>0){
					show();
					text_24.setText("");
					combo.removeAll();
					combo_1.removeAll();
					combo_2.removeAll();
					combo_3.removeAll();
					combo_4.removeAll();
					combo_5.removeAll();
					DBHelper db=new DBHelper();
					List<Map<String,String>> list=new ArrayList<Map<String,String>>();
					String sql="select s_name from station where condition!='暂停使用'";
					list=db.finds(sql, null);
					for(int i=0;i<list.size();i++){
						combo.add(list.get(i).get("s_name"));
						combo_1.add(list.get(i).get("s_name"));
						combo_2.add(list.get(i).get("s_name"));
						combo_3.add(list.get(i).get("s_name"));
						combo_4.add(list.get(i).get("s_name"));
						combo_5.add(list.get(i).get("s_name"));
					}
				}
			}
		});
		//删除站点
		menuItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				TableItem[] tis=table.getSelection();
				if(tis!=null&&tis.length>0){
					String s_ID ="";
					for(TableItem ti:tis){
						s_ID+=ti.getText(0)+",";
					}
					s_ID=s_ID.substring(0,s_ID.length()-1);
					DeleteStation deleteStation=new DeleteStation();
	//				System.out.println(s_ID);
					deleteStation.open(s_ID);
				}else{
					MessageDialog.openError(shell, "温馨提示", "请选中您要删除的数据。。");
				}
			}
		});
		
		text.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				StationinfoDBH stationinfoDBH=new StationinfoDBH();
				if(stationinfoDBH.delete(text.getText().trim())>0){
					show();
					combo.removeAll();
					combo_1.removeAll();
					combo_2.removeAll();
					combo_3.removeAll();
					combo_4.removeAll();
					combo_5.removeAll();
					DBHelper db=new DBHelper();
					List<Map<String,String>> list=new ArrayList<Map<String,String>>();
					String sql="select s_name from station where condition!='暂停使用'";
					list=db.finds(sql, null);
					for(int i=0;i<list.size();i++){
						combo.add(list.get(i).get("s_name"));
						combo_1.add(list.get(i).get("s_name"));
						combo_2.add(list.get(i).get("s_name"));
						combo_3.add(list.get(i).get("s_name"));
						combo_4.add(list.get(i).get("s_name"));
						combo_5.add(list.get(i).get("s_name"));
					}
				}else{
					MessageDialog.openError(shell, "失败提示", "站点删除失败");
				}
			}
		});

		button_2.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				TrainDBH trainDBH=new TrainDBH();
				String t_ID=text_35.getText().trim();
				String start_day=calendarCombo.getDateAsString();
				String end_day=calendarCombo_1.getDateAsString();
				int times=Integer.parseInt(text_23.getText().trim());
				if(trainDBH.add(t_ID, start_day, end_day,times)>0){
					System.out.println("++++++++++++++++++++++++++++++++++==========");
		            combo_6.removeAll();
					MessageDialog.openInformation(shell, "成功提示", "火车添加成功");
					DBHelper db=new DBHelper();
					List<Map<String,String>> list1=new ArrayList<Map<String,String>>();
					String sql1="select t_ID from train t where t.condition='初始化中'";
					list1=db.finds(sql1, null);
					for(int i=0;i<list1.size();i++){
						combo_6.add(list1.get(i).get("t_id"));
					}
					text_35.setText("");
					calendarCombo.setText("");
					calendarCombo_1.setText("");
				}else{
					MessageDialog.openError(shell, "失败提示", "火车添加失败！！！");
				}
			}
		});

		combo.removeAll();
		combo_1.removeAll();
		combo_2.removeAll();
		combo_3.removeAll();
		combo_4.removeAll();
		combo_5.removeAll();
		DBHelper db=new DBHelper();
		List<Map<String,String>> list=new ArrayList<Map<String,String>>();
		String sql="select s_name from station where condition!='暂停使用'";
		list=db.finds(sql, null);
		for(int i=0;i<list.size();i++){
			combo.add(list.get(i).get("s_name"));
			combo_1.add(list.get(i).get("s_name"));
			combo_2.add(list.get(i).get("s_name"));
			combo_3.add(list.get(i).get("s_name"));
			combo_4.add(list.get(i).get("s_name"));
			combo_5.add(list.get(i).get("s_name"));
		}
		List<Map<String,String>> list1=new ArrayList<Map<String,String>>();
		String sql1="select t_ID from train t where t.condition='初始化中'";
		list1=db.finds(sql1, null);
		for(int i=0;i<list1.size();i++){
			combo_6.add(list1.get(i).get("t_id"));
		}


			combo_1.addModifyListener(new ModifyListener() {
				public void modifyText(ModifyEvent arg0) {
					text_25.setEditable(true);
					text_26.setEditable(true);
					text_8.setEditable(true);
					text_13.setEditable(true);
					text_18.setEditable(true);
					String s_name=combo_1.getText().trim();
					text_4.setText(s_name);
				}
			});
			combo_2.addModifyListener(new ModifyListener() {
				public void modifyText(ModifyEvent arg0) {
					text_27.setEditable(true);
					text_28.setEditable(true);
					text_9.setEditable(true);
					text_14.setEditable(true);
					text_19.setEditable(true);
					String s_name=combo_2.getText().trim();
					text_5.setText(s_name);
				}
			});
			combo_3.addModifyListener(new ModifyListener() {
				public void modifyText(ModifyEvent arg0) {
					text_29.setEditable(true);
					text_30.setEditable(true);
					text_10.setEditable(true);
					text_15.setEditable(true);
					text_20.setEditable(true);
					String s_name=combo_3.getText().trim();
					text_6.setText(s_name);
				}
			});
			combo_4.addModifyListener(new ModifyListener() {
				public void modifyText(ModifyEvent arg0) {
					text_31.setEditable(true);
					text_32.setEditable(true);
					text_11.setEditable(true);
					text_16.setEditable(true);
					text_21.setEditable(true);
					String s_name=combo_4.getText().trim();
					text_7.setText(s_name);
				}
			});
			combo_5.addModifyListener(new ModifyListener() {
				public void modifyText(ModifyEvent arg0) {
					text_33.setEditable(true);
					text_34.setEditable(true);
					text_12.setEditable(true);
					text_17.setEditable(true);
					text_22.setEditable(true);
				}
			});

			text_26.addFocusListener(new FocusAdapter() {
				public void focusLost(FocusEvent e) {
					String time2=text_26.getText().trim();
					String time1=text_25.getText().trim();
					if(time(time1,time2)){
						bll=true;
						label_27.setText("");
					}else{
						bll=false;
						label_27.setText("错误");
					}
				}
			});
			text_27.addFocusListener(new FocusAdapter() {
				public void focusLost(FocusEvent e) {
					String time2=text_27.getText().trim();
					String time1=text_26.getText().trim();
					if(time(time1,time2)){
						label_27.setText("");
					}else{
						label_27.setText("错误");
					}
				}
			});
			text_28.addFocusListener(new FocusAdapter() {
				public void focusLost(FocusEvent e) {
					String time2=text_28.getText().trim();
					String time1=text_27.getText().trim();
					if(time(time1,time2)){
						label_28.setText("");
					}else{
						label_28.setText("错误");
					}
				}
			});
			text_29.addFocusListener(new FocusAdapter() {
				public void focusLost(FocusEvent e) {
					String time2=text_29.getText().trim();
					String time1=text_28.getText().trim();
					if(time(time1,time2)){
						label_28.setText("");
					}else{
						label_28.setText("错误");
					}
				}
			});
			text_30.addFocusListener(new FocusAdapter() {
				public void focusLost(FocusEvent e) {
					String time2=text_30.getText().trim();
					String time1=text_29.getText().trim();
					if(time(time1,time2)){
						label_29.setText("");
					}else{
						label_29.setText("错误");
					}
				}
			});
			text_31.addFocusListener(new FocusAdapter() {
				public void focusLost(FocusEvent e) {
					String time2=text_31.getText().trim();
					String time1=text_30.getText().trim();
					if(time(time1,time2)){
						label_29.setText("");
					}else{
						label_29.setText("错误");
					}
				}
			});
			text_32.addFocusListener(new FocusAdapter() {
				public void focusLost(FocusEvent e) {
					String time2=text_32.getText().trim();
					String time1=text_31.getText().trim();
					if(time(time1,time2)){
						label_30.setText("");
					}else{
						label_30.setText("错误");
					}
				}
			});
			text_33.addFocusListener(new FocusAdapter() {
				public void focusLost(FocusEvent e) {
					String time2=text_33.getText().trim();
					String time1=text_32.getText().trim();
					if(time(time1,time2)){
						label_30.setText("");
					}else{
						label_30.setText("错误");
					}
				}
			});
			text_34.addFocusListener(new FocusAdapter() {
				public void focusLost(FocusEvent e) {
					String time2=text_34.getText().trim();
					String time1=text_33.getText().trim();
					if(time(time1,time2)){
						label_31.setText("");
					}else{
						label_31.setText("错误");
					}
				}
			});

			text_3.addFocusListener(new FocusAdapter() {
				public void focusLost(FocusEvent e) {
					if(text_3.getText().trim()!=null&&!"".equals(text_3.getText().trim())){
						try{
							int soft_seat=Integer.parseInt(text_3.getText().trim());
							if(soft_seat>0){
								label_32.setText("");
							}else{
								label_32.setText("错误");
							}
							isOK();
						}catch(NumberFormatException e1){
							label_32.setText("错误");
						}
					}
				}
			});
			text_1.addFocusListener(new FocusAdapter() {
				public void focusLost(FocusEvent e) {
					try{
						int soft_seat=Integer.parseInt(text_1.getText().trim());
						if(soft_seat>0){
							label_33.setText("");
						}else{
							label_33.setText("错误");
						}
						isOK();
					}catch(NumberFormatException e1){
						label_33.setText("错误");
					}
				}
			});
			text_2.addFocusListener(new FocusAdapter() {
				public void focusLost(FocusEvent e) {
					try{
						int soft_seat=Integer.parseInt(text_2.getText().trim());
						if(soft_seat>0){
							label_34.setText("");
						}else{
							label_34.setText("错误");
						}
						isOK();
					}catch(NumberFormatException e1){
						label_34.setText("错误");
					}
				}
			});	

			combo_6.addModifyListener(new ModifyListener() {
				public void modifyText(ModifyEvent arg0) {
					String t_ID=combo_6.getText().trim();
					if(t_ID!=null&&!("".equals(t_ID))){
						blll[0]=true;
					}else{
						blll[0]=false;
					}
					if(!("".equals(text_1.getText().trim())||"".equals(text_2.getText().trim())||"".equals(text_3.getText().trim())||"无".equals(text_1.getText().trim())||"无".equals(text_2.getText().trim())||"无".equals(text_3.getText().trim()))){
						if(Integer.parseInt(text_1.getText().trim())<=0||Integer.parseInt(text_2.getText().trim())<=0||Integer.parseInt(text_3.getText().trim())<=0){
							blll[1]=false;
						}else{
							blll[1]=true;
						}
					}
					isOK();
				}
			});

			button.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					String ss=combo.getText().trim()+"--"+combo_1.getText().trim();
					if(combo_2.getText()!=null&&!combo_2.getText().equals("")){
						ss=ss+"---"+combo_2.getText().trim();
					}
					if(combo_3.getText()!=null&&!combo_3.getText().equals("")){
						ss=ss+"---"+combo_3.getText().trim();
					}
					if(combo_4.getText()!=null&&!combo_4.getText().equals("")){
						ss=ss+"---"+combo_4.getText().trim();
					}
					if(combo_5.getText()!=null&&!combo_5.getText().equals("")){
						ss=ss+"---"+combo_5.getText().trim();
					}
					lblNewLabel_1.setText(ss);
					button_1.setEnabled(true);
				}
			});

			button_1.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
//					prices[0]=Double.parseDouble(text_8.getText().trim());
//					prices[1]=Double.parseDouble(text_9.getText().trim());
//					prices[2]=Double.parseDouble(text_10.getText().trim());
//					prices[3]=Double.parseDouble(text_11.getText().trim());
//					prices[4]=Double.parseDouble(text_12.getText().trim());
//					prices[5]=Double.parseDouble(text_13.getText().trim());
//					prices[6]=Double.parseDouble(text_14.getText().trim());
//					prices[7]=Double.parseDouble(text_15.getText().trim());
//					prices[8]=Double.parseDouble(text_16.getText().trim());
//					prices[9]=Double.parseDouble(text_17.getText().trim());
//					prices[10]=Double.parseDouble(text_18.getText().trim());
//					prices[11]=Double.parseDouble(text_19.getText().trim());
//					prices[12]=Double.parseDouble(text_20.getText().trim());
//					prices[13]=Double.parseDouble(text_21.getText().trim());
//					prices[14]=Double.parseDouble(text_22.getText().trim());
					
					//				for(double price:prices){
					//					if("".equals(price)){
					//						MessageDialog.openError(shell, "失败提示", "票价错误。。。");
					//						break;
					//					}
					//				}
					TraininfoDBH traininfoDBH=new TraininfoDBH();
					TrainDBH trainDBH=new TrainDBH();
					String t_ID=combo_6.getText().trim();
					String [] t_init=new String[5] ;
					t_init[0]=combo.getText().trim();
					t_init[1]=text_4.getText().trim();
					t_init[2]=text_5.getText().trim();
					t_init[3]=text_6.getText().trim();
					t_init[4]=text_7.getText().trim();
					String [] t_dest=new String [5];
					t_dest[0]=combo_1.getText().trim();
					t_dest[1]=combo_2.getText().trim();
					t_dest[2]=combo_3.getText().trim();
					t_dest[3]=combo_4.getText().trim();
					t_dest[4]=combo_5.getText().trim();
					System.out.println(text_13.getText().trim());
					System.out.println(text_14.getText().trim());
					System.out.println(text_15.getText().trim());
					System.out.println(text_16.getText().trim());
					System.out.println(text_17.getText().trim());
					System.out.println(text_18.getText().trim());
					System.out.println(text_19.getText().trim());
					System.out.println(text_20.getText().trim());
					System.out.println(text_21.getText().trim());
					System.out.println(text_22.getText().trim());

					double soft_price[]=new double[5];
					double hard_price[]=new double[5];
					double seat_price[]=new double[5];
					try{
					soft_price[0]=Double.parseDouble(text_8.getText().trim());
					soft_price[1]=Double.parseDouble(text_9.getText().trim());
					soft_price[2]=Double.parseDouble(text_10.getText().trim());
					soft_price[3]=Double.parseDouble(text_11.getText().trim());
					soft_price[4]=Double.parseDouble(text_12.getText().trim());
					hard_price[0]=Double.parseDouble(text_13.getText().trim());
					hard_price[1]=Double.parseDouble(text_14.getText().trim());
					hard_price[2]=Double.parseDouble(text_15.getText().trim());
					hard_price[3]=Double.parseDouble(text_16.getText().trim());
					hard_price[4]=Double.parseDouble(text_17.getText().trim());
					seat_price[0]=Double.parseDouble(text_18.getText().trim());
					seat_price[1]=Double.parseDouble(text_19.getText().trim());
					seat_price[2]=Double.parseDouble(text_20.getText().trim());
					seat_price[3]=Double.parseDouble(text_21.getText().trim());
					seat_price[4]=Double.parseDouble(text_22.getText().trim());
					}catch(NumberFormatException e1){
						System.out.println(soft_price[0]+"--"+soft_price[1]+"--"+soft_price[2]+"--"+soft_price[3]+"--"+soft_price[4]+"--"+hard_price[0]+"--"+hard_price[1]+"--"+hard_price[2]);
					}
					String start_time[]=new String[5];
					start_time[0]=text_25.getText().trim();
					start_time[1]=text_27.getText().trim();
					start_time[2]=text_29.getText().trim();
					start_time[3]=text_31.getText().trim();
					start_time[4]=text_33.getText().trim();
					String end_time[]=new String[5];
					end_time[0]=text_26.getText().trim();
					end_time[1]=text_28.getText().trim();
					end_time[2]=text_30.getText().trim();
					end_time[3]=text_32.getText().trim();
					end_time[4]=text_34.getText().trim();

					for(int i=1;i<6;i++){
	//					System.out.println("------------------");
						if((end_time[i-1]!=null && start_time[i-1]!=null)&&(!"".equals(end_time[i-1]) && !"".equals(start_time[i-1]))){
							traininfoDBH.add(i, t_ID, t_init[i-1], t_dest[i-1], soft_price[i-1], hard_price[i-1], seat_price[i-1], start_time[i-1], end_time[i-1]);
						}else{
							DBHelper db=new DBHelper();
							int soft_seat=Integer.parseInt(text_3.getText().trim());
							int head_seat=Integer.parseInt(text_1.getText().trim());
							int t_seat=Integer.parseInt(text_2.getText().trim());
							System.out.println(soft_seat+"--"+head_seat+"--"+t_seat);
                            String sql="update traininfo set soft_seat=?,hard_seat=?,t_seat=? where t_ID=?";
                            String sql1="update train set soft_seat=?,hard_seat=?,t_seat=?,condition='运营中' where t_ID=?";
							List<Object> params=new ArrayList<Object>();
							List<Object> params1=new ArrayList<Object>();
							params.add(soft_seat);
							params.add(head_seat);
							params.add(t_seat);
							params.add(t_ID);
							params1.add(soft_seat);
							params1.add(head_seat);
							params1.add(t_seat);
							params1.add(t_ID);
							System.out.println(db.update(sql1, params1));
							System.out.println(db.update(sql, params));
							if(db.update(sql1, params1)>0&&db.update(sql, params)>0){
								MessageDialog.openInformation(shell, "成功提示", "火车准备开工！！！"); 
								combo.setText(" ");
								text_4.setText("");
								text_5.setText("");
								text_6.setText("");
								text_7.setText("");
								combo_1.setText(" ");
								combo_2.setText(" ");
								combo_3.setText(" ");
								combo_4.setText(" ");
								combo_5.setText(" ");
								text_8.setText("0");
								text_9.setText("0");
								text_10.setText("0");
								text_11.setText("0");
								text_12.setText("0");
								text_13.setText("0");
								text_14.setText("0");
								text_15.setText("0");
								text_16.setText("0");
								text_17.setText("0");
							    text_18.setText("0");
								text_19.setText("0");
								text_20.setText("0");
								text_21.setText("0");
								text_22.setText("0");
								text_25.setText("");
								text_27.setText("");
								text_29.setText("");
								text_31.setText("");
								text_33.setText("");
								text_26.setText("");
								text_28.setText("");
								text_30.setText("");
								text_32.setText("");
								text_34.setText("");
								combo_6.removeAll();
								List<Map<String,String>> list=new ArrayList<Map<String,String>>();
								String sql2="select t_ID from train where condition='初始化中'";
								list=db.finds(sql2, null);
								System.out.println(list.size());
								System.out.println(list);
								for(int j=0,len=list.size();j<len;j++){
									combo_6.add(list.get(j).get("t_id"));
								}
							}else if(db.update(sql1, params1)==0&& db.update(sql, params)>0){
								sql="delete traininfo where t_ID=?";
								List<Object> params2=new ArrayList<Object>();
                                params.add(t_ID);
                                db.update(sql, params2);
								MessageDialog.openError(shell, "错误提示", "火车还未制造出来！！！"); 
							}
							break;
						}
					}
				}
			});	
		show();
	}
	//判断
	public void isOK(){
		if(!bll){
			button.setEnabled(false);
			return;
		}
		for(boolean b:blll){
			if(!b){
				button.setEnabled(false);
				return;
			}
		}
		if("错误".equals(text_33.getText())||"错误".equals(text_32.getText())||"错误".equals(text_34.getText())){
			button.setEnabled(false);
		}
		button.setEnabled(true);
		return;
	}

	//显示站点
	public void show(){
		table.removeAll();
		StationinfoDBH stationinfoDBH=new StationinfoDBH();
		List<Map<String,String>> list=stationinfoDBH.find();
		if(list!=null && list.size()>0){
			TableItem ti;
			for(Map<String,String> map:list){
				ti=new TableItem(table,SWT.NONE);
				ti.setText(new String[]{String.valueOf( map.get("s_id")),String.valueOf( map.get("s_name"))});
			}
		}
	}

	public boolean time(String time1,String time2){
		DBHelper db=new DBHelper();
		List<Object> params=new ArrayList<Object>();
		String sql="select round(to_number(to_date(?,'hh24:mi:ss')-to_date(?,'hh24:mi:ss'))*24*60) FROM dual";
		params.add(time2);
		params.add(time1);
		if(db.getTotal(sql, params)>0){
			return true;
		}
		return false;
	}
}
//combo_1.addFocusListener(new FocusAdapter() {
//public void focusLost(FocusEvent e)  {
//	StationinfoDBH stationinfoDBH=new StationinfoDBH();
//	String s_name=combo_1.getText().trim();
//	Map<String,String> map=stationinfoDBH.finds(s_name);
//		if(map!=null && map.size()>0){
//			text_4.setText(s_name);
//			label_22.setText("");
//			bl[0]=true;
//		}else{
//			label_22.setText("站点不存在");
//			bl[0]=false;
//		}
//}
//});
//combo_2.addFocusListener(new FocusAdapter() {
//public void focusLost(FocusEvent e) {
//	StationinfoDBH stationinfoDBH=new StationinfoDBH();
//	String s_name=combo_2.getText().trim();
//	Map<String,String> map=stationinfoDBH.finds(s_name);
//		if(map!=null && map.size()>0||"无".equals(s_name)){
//			text_5.setText(s_name);
//			label_23.setText("");
//			bl[1]=true;
//		}else{
//			label_23.setText("站点不存在");
//			bl[1]=false;
//		}
//}
//});
//combo_3.addFocusListener(new FocusAdapter() {
//public void focusLost(FocusEvent e) {
//	StationinfoDBH stationinfoDBH=new StationinfoDBH();
//	String s_name=combo_3.getText().trim();
//	Map<String,String> map=stationinfoDBH.finds(s_name);
//		if(map!=null && map.size()>0||"无".equals(s_name)){
//			text_6.setText(s_name);
//			label_24.setText("");
//			bl[2]=true;
//		}else{
//			label_24.setText("站点不存在");
//			bl[2]=false;
//		}
//}
//});
//combo_4.addFocusListener(new FocusAdapter() {
//public void focusLost(FocusEvent e) {
//	StationinfoDBH stationinfoDBH=new StationinfoDBH();
//	String s_name=combo_4.getText().trim();
//	Map<String,String> map=stationinfoDBH.finds(s_name);
//		if(map!=null && map.size()>0||"无".equals(s_name)){
//			text_7.setText(s_name);
//			label_25.setText("");
//			bl[3]=true;
//		}else{
//			label_25.setText("站点不存在");
//			bl[3]=false;
//		}
//}
//});
//combo_5.addFocusListener(new FocusAdapter() {
//public void focusLost(FocusEvent e) {
//	StationinfoDBH stationinfoDBH=new StationinfoDBH();
//	String s_name=combo_5.getText().trim();
//	Map<String,String> map=stationinfoDBH.finds(s_name);
//		if(map!=null && map.size()>0||"无".equals(s_name)){
//		//	text_8.setText(s_name);
//			label_26.setText("");
//			bl[4]=true;
//		}else{
//			label_26.setText("站点不存在");
//			bl[4]=false;
//		}
//		isOK();
//}
//});

//if(combo_1.getText().trim()!=null && !("".equals(combo_1.getText().trim()))){
//text_25.setEnabled(true);
//text_26.setEditable(true);
//}else{
//text_25.setEditable(false);
//text_26.setEditable(false);
//}
//if(combo_2.getText().trim()!=null && !("".equals(combo_1.getText().trim()))){
//text_27.setEditable(true);
//text_28.setEditable(true);
//}else{
//text_27.setEditable(false);
//text_28.setEditable(false);
//}
//if(combo_3.getText().trim()!=null && !("".equals(combo_1.getText().trim()))){
//text_29.setEditable(true);
//text_30.setEditable(true);
//}else{
//text_29.setEditable(false);
//text_30.setEditable(false);
//}
//if(combo_4.getText().trim()!=null && !("".equals(combo_1.getText().trim()))){
//text_31.setEditable(true);
//text_32.setEditable(true);
//}else{
//text_31.setEditable(false);
//text_32.setEditable(false);
//}
//if(combo_5.getText().trim()!=null && !("".equals(combo_1.getText().trim()))){
//text_33.setEditable(true);
//text_34.setEditable(true);
//}else{
//text_33.setEditable(false);
//text_34.setEditable(false);
//}