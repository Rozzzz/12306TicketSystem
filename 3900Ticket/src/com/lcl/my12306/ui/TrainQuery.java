package com.lcl.my12306.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.custom.SashForm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.wb.swt.SWTResourceManager;

import com.lcl.my12306.dao.DBHelper;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class TrainQuery {

	protected Shell shell;
	private Display display;
	private Table table;
	private Table table_1;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			TrainQuery window = new TrainQuery();
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
		shell.setImage(SWTResourceManager.getImage(TrainQuery.class, "/image/12306_logo.jpg"));
		shell.setSize(450, 300);
		shell.setText("列车查询");
		//全屏
		shell.setBounds(0,0,display.getClientArea().width,display.getClientArea().height);
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		TabFolder tabFolder = new TabFolder(shell, SWT.NONE);
		
		Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		
		TabItem tabItem = new TabItem(tabFolder, SWT.NONE);
		tabItem.setText("车次查询");
		
		SashForm sashForm = new SashForm(tabFolder, SWT.NONE);
		sashForm.setOrientation(SWT.VERTICAL);
		tabItem.setControl(sashForm);
		
		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite.setBackgroundImage(SWTResourceManager.getImage(TrainQuery.class, "/image/b13.jpg"));
		
		Label label = new Label(composite, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.BOLD));
		label.setBounds(109, 28, 61, 22);
		label.setText("车    次");
		
		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.BOLD));
		label_1.setBounds(100, 70, 70, 22);
		label_1.setText("所经站点");
		
		final Combo combo = new Combo(composite, SWT.NONE);
		combo.setBounds(185, 29, 95, 25);
		
		final Label lblNewLabel = new Label(composite, SWT.BORDER);
		lblNewLabel.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		lblNewLabel.setBounds(185, 70, 409, 25);
		
		Label label_3 = new Label(composite, SWT.NONE);
		
		label_3.setBackground(SWTResourceManager.getColor(245, 222, 179));
		label_3.setAlignment(SWT.CENTER);
		label_3.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_3.setBounds(644, 70, 144, 22);
		label_3.setText("返回添加列车信息");
		
		Button btnNewButton = new Button(composite, SWT.NONE);
		btnNewButton.setBounds(329, 28, 80, 27);
		btnNewButton.setText("查  询");
		
		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblNewLabel_1.setBounds(446, 33, 159, 17);
		lblNewLabel_1.setText("如改变信息请重新点击查询");
		
		Composite composite_2 = new Composite(sashForm, SWT.NONE);
		composite_2.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		table = new Table(composite_2, SWT.BORDER | SWT.FULL_SELECTION);
		table.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(132);
		tableColumn.setText("车次");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(143);
		tableColumn_2.setText("状态");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(168);
		tableColumn_1.setText("开始日期");
		
		TableColumn tableColumn_13 = new TableColumn(table, SWT.NONE);
		tableColumn_13.setWidth(168);
		tableColumn_13.setText("结束日期");
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(191);
		tblclmnNewColumn.setText("软卧票数");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(210);
		tableColumn_3.setText("硬卧票数");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(163);
		tblclmnNewColumn_1.setText("硬座票数");
		
		Menu menu = new Menu(table);
		table.setMenu(menu);
		
		MenuItem menuItem = new MenuItem(menu, SWT.NONE);
		menuItem.setText("修改");
		
		TableColumn tableColumn_4 = new TableColumn(table, SWT.NONE);
		tableColumn_4.setWidth(222);
		tableColumn_4.setText("备注");
		sashForm.setWeights(new int[] {170, 487});
		
		TabItem tbtmNewItem = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem.setText("日期查询");
		
		SashForm sashForm_1 = new SashForm(tabFolder, SWT.NONE);
		sashForm_1.setOrientation(SWT.VERTICAL);
		tbtmNewItem.setControl(sashForm_1);
		
		Composite composite_3 = new Composite(sashForm_1, SWT.NONE);
		composite_3.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite_3.setBackgroundImage(SWTResourceManager.getImage(TrainQuery.class, "/image/b13.jpg"));
		
		Label label_5 = new Label(composite_3, SWT.NONE);
		label_5.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.BOLD));
		label_5.setBounds(72, 39, 53, 22);
		label_5.setText("日  期");
		
		Combo combo_1 = new Combo(composite_3, SWT.NONE);
		combo_1.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		combo_1.setBounds(140, 39, 128, 25);
		
		Label label_6 = new Label(composite_3, SWT.NONE);
		label_6.setText("查  询");
		label_6.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_6.setAlignment(SWT.CENTER);
		label_6.setBounds(310, 39, 88, 22);
		
		Label label_7 = new Label(composite_3, SWT.NONE);
		
		label_7.setBackground(SWTResourceManager.getColor(245, 222, 179));
		label_7.setText("返回添加列车信息");
		label_7.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_7.setAlignment(SWT.CENTER);
		label_7.setBounds(439, 39, 144, 22);
		
		Composite composite_4 = new Composite(sashForm_1, SWT.NONE);
		
		table_1 = new Table(composite_4, SWT.BORDER | SWT.FULL_SELECTION);
		table_1.setLinesVisible(true);
		table_1.setHeaderVisible(true);
		table_1.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		table_1.setBounds(0, 0, 1342, 487);
		
		TableColumn tableColumn_5 = new TableColumn(table_1, SWT.NONE);
		tableColumn_5.setWidth(168);
		tableColumn_5.setText("车次");
		
		TableColumn tableColumn_6 = new TableColumn(table_1, SWT.NONE);
		tableColumn_6.setWidth(168);
		tableColumn_6.setText("所经站点");
		
		TableColumn tableColumn_7 = new TableColumn(table_1, SWT.NONE);
		tableColumn_7.setWidth(168);
		tableColumn_7.setText("软卧票数");
		
		TableColumn tableColumn_8 = new TableColumn(table_1, SWT.NONE);
		tableColumn_8.setWidth(168);
		tableColumn_8.setText("软卧余票");
		
		TableColumn tableColumn_9 = new TableColumn(table_1, SWT.NONE);
		tableColumn_9.setWidth(168);
		tableColumn_9.setText("硬卧票数");
		
		TableColumn tableColumn_10 = new TableColumn(table_1, SWT.NONE);
		tableColumn_10.setWidth(167);
		tableColumn_10.setText("硬卧余票");
		
		TableColumn tableColumn_11 = new TableColumn(table_1, SWT.NONE);
		tableColumn_11.setWidth(167);
		tableColumn_11.setText("硬座票数");
		
		TableColumn tableColumn_12 = new TableColumn(table_1, SWT.NONE);
		tableColumn_12.setWidth(167);
		tableColumn_12.setText("硬座余票");
		
		Menu menu_1 = new Menu(table_1);
		table_1.setMenu(menu_1);
		
		MenuItem menuItem_1 = new MenuItem(menu_1, SWT.NONE);
		menuItem_1.setText("删除");
		sashForm_1.setWeights(new int[] {110, 547});
		
		DBHelper db=new DBHelper();
		List<Map<String,String>> list=new ArrayList<Map<String,String>>();
		String sql2="select t_ID from train where condition='运营中'";
		list=db.finds(sql2, null);
		System.out.println(list.size());
		System.out.println(list);
		for(int j=0,len=list.size();j<len;j++){
			combo.add(list.get(j).get("t_id"));
		}
		
		//点击添加列车信息
		final TrainManage trainManage=new TrainManage();
		label_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				shell.close();
				trainManage.open();
			}
		});
		label_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				shell.close();
				trainManage.open();
			}
		});
		//点击查询
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				table.clearAll();
				table.removeAll();
				TableItem ti;
				DBHelper db=new DBHelper();
	    //显示所经站点
				String sql="SELECT MAX(dest_ID) FROM traininfo WHERE t_ID=?";
				List<Object> params=new ArrayList<Object>();
				String t_ID=combo.getText().trim();
				params.add(t_ID);
				double len=db.getTotal(sql, params);
			//	if(len==null){
				String statininfo="";
				for(int i=1;i<=len-1;i++){
					String sql1="SELECT t_init ,t_dest FROM traininfo WHERE t_ID=? AND init_ID=?";
					List<Object> params1=new ArrayList<Object>();
					System.out.println("--------------------");
					System.out.println(String.valueOf(t_ID));
					System.out.println(i);
					System.out.println("=======================");
					params1.add(String.valueOf(t_ID));
					params1.add(String.valueOf(i));
					if(i==len){
					    statininfo+=db.find(sql1, params1).get("t_init")+"---"+db.find(sql1, params1).get("t_dest");
					}else{
					    statininfo+=db.find(sql1, params1).get("t_init")+"---";
					}
				}
				lblNewLabel.setText(statininfo);
		//显示表中信息		
			//	System.out.println("*******************888");
				String sql2="SELECT t_ID,start_day,end_day,soft_seat,hard_seat,t_seat,condition,times FROM train WHERE t_ID=?";
				List<Object> params2=new ArrayList<Object>();
				params2.add(t_ID);
				List<Map<String,String>> list=new ArrayList<Map<String,String>>();
				list=db.finds(sql2, params2);
				String start_day=list.get(0).get("start_day");
				String end_day=list.get(0).get("end_day");
				String sql3="select round(to_number(to_date(?,'YYYY-MM-DD')-to_date(?,'YYYY-MM-DD')) )FROM dual";
				String sql4="SELECT to_char(to_date(?,'yyyy-mm-dd')+?,'yyyy-mm-dd')SYSDATE2 FROM dual";
				String sql5="select * from t_daily_report where t_ID=?";
				List<Object> params3=new ArrayList<Object>();
				List<Object> params5=new ArrayList<Object>();
				params3.add(end_day);
				params3.add(start_day);
				params5.add(t_ID);
				List<Map<String,String>> list1=db.finds(sql5, params5);
				double len1 =db.getTotal(sql3, params3);
				//			System.out.println(len1);
				System.out.println(list1);
				if(list1==null||list1.size()==0){
					for(int i=0;i<=len1;i++){
						List<Object> params4=new ArrayList<Object>();
						params4.add(String.valueOf(list.get(0).get("start_day")));
						params4.add(i);
						Map<String,String> map=db.find(sql4, params4);
						ti=new TableItem(table,SWT.NONE);
						ti.setText(new String[]{String.valueOf(list.get(0).get("t_id")),String.valueOf(list.get(0).get("condition")),
								String.valueOf(map.get("sysdate2")),String.valueOf("无"),String.valueOf(list.get(0).get("soft_seat")),
								String.valueOf(list.get(0).get("hard_seat")),String.valueOf(list.get(0).get("t_seat")),
								String.valueOf("无")});
					}
				}else{
					for(int i=0;i<=len1;i++){
						int count=0;
						List<Object> params4=new ArrayList<Object>();
						params4.add(String.valueOf(list.get(0).get("start_day")));
						params4.add(i);
						Map<String,String> map=db.find(sql4, params4);
						SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                        Date sysdate2=new Date();
                        Date start_dates=new Date();
                        Date end_dates=new Date();
						for(int j=0,len11=list1.size();j<len11;j++){
							if(count==1){
								break;
							}
							 try {
                            	if(!"无".equals(list1.get(j).get("end_dates"))){
                            		end_dates=sdf.parse(list1.get(j).get("end_dates"));
                            	}
                            	sysdate2=sdf.parse(map.get("sysdate2"));
                            	start_dates=sdf.parse(list1.get(j).get("start_dates"));
                            } catch (ParseException e1) {
                            	e1.printStackTrace();
                            }
							if(sysdate2.compareTo(start_dates)==0&&"无".equals(list1.get(j).get("end_dates"))&&count==0){
								count++;
								ti=new TableItem(table,SWT.NONE);
								ti.setText(new String[]{String.valueOf(list.get(0).get("t_id")),String.valueOf(list1.get(j).get("defect")),
										String.valueOf(map.get("sysdate2")),String.valueOf("无"),String.valueOf(list.get(0).get("soft_seat")),
										String.valueOf(list.get(0).get("hard_seat")),String.valueOf(list.get(0).get("t_seat")),
										String.valueOf(list1.get(j).get("note"))});
							}else if(sysdate2.compareTo(start_dates)==0&&sysdate2.compareTo(end_dates)<=0&&count==0){
								System.out.println("--------------------------");
								System.out.println(sysdate2.compareTo(start_dates)>=0);
								System.out.println(sysdate2.compareTo(end_dates)<=0);
								count++;
								ti=new TableItem(table,SWT.NONE);
								ti.setText(new String[]{String.valueOf(list.get(0).get("t_id")),String.valueOf(list1.get(j).get("defect")),
										String.valueOf(list1.get(j).get("start_dates")),String.valueOf(list1.get(j).get("end_dates")),String.valueOf(list.get(0).get("soft_seat")),
										String.valueOf(list.get(0).get("hard_seat")),String.valueOf(list.get(0).get("t_seat")),
										String.valueOf(list1.get(j).get("note"))});
							}else if(sysdate2.compareTo(start_dates)>0&&sysdate2.compareTo(end_dates)<=0&&count==0){
								count++;
							}else if(count==0&&j==list1.size()-1){
								count++;
								ti=new TableItem(table,SWT.NONE);
								ti.setText(new String[]{String.valueOf(list.get(0).get("t_id")),String.valueOf(list.get(0).get("condition")),
										String.valueOf(map.get("sysdate2")),String.valueOf("无"),String.valueOf(list.get(0).get("soft_seat")),
										String.valueOf(list.get(0).get("hard_seat")),String.valueOf(list.get(0).get("t_seat")),
										String.valueOf("无")});
							}
						}
					}
				}
			}
		});
		menuItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				T_daily_report t_daily_report=new T_daily_report();
				TableItem[] tis=table.getSelection();
				System.out.println(tis[0].getImageIndent());
				String info ="";
				if(tis!=null&&tis.length>0){
					for(int i=0;i<8;i++){
						info+=tis[0].getText(i)+",";
					}
					String []strArr=info.split(",");
                    if(strArr[1]=="因故停运"){
                    	MessageDialog.openError(shell, "温馨提示", "无法修改已停运的记录。。");
                    }else{
        				t_daily_report.open(info);
                    }
				}else{
					MessageDialog.openError(shell, "温馨提示", "请选中您要操作的数据。。");
				}
			}
		});
	}
}
