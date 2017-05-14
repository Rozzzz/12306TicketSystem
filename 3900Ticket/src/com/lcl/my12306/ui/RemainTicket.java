package com.lcl.my12306.ui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.custom.SashForm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

import com.lcl.my12306.dao.DBHelper;
import com.lcl.my12306.dao.StationinfoDBH;
import com.lcl.my12306.dao.TrainDBH;
import com.lcl.my12306.dao.TraininfoDBH;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.nebula.widgets.calendarcombo.CalendarCombo;

public class RemainTicket extends Composite {
	private Table table;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public RemainTicket(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(this, SWT.NONE);
		sashForm.setOrientation(SWT.VERTICAL);
		
		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite.setBackgroundImage(SWTResourceManager.getImage(RemainTicket.class, "/image/b11.jpg"));
		
		Label label = new Label(composite, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.BOLD));
		label.setBounds(10, 25, 54, 25);
		label.setText("出发地");
		
		final Combo combo = new Combo(composite, SWT.NONE);
		combo.setBounds(70, 26, 80, 25);
		
		Label label_1 = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_1.setBounds(170, 35, 64, 2);
		
		Label label_2 = new Label(composite, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.BOLD));
		label_2.setBounds(250, 25, 57, 25);
		label_2.setText("目的地");
		
		final Combo combo_1 = new Combo(composite, SWT.NONE);
		combo_1.setBounds(308, 25, 80, 25);
		
		Label label_3 = new Label(composite, SWT.NONE);
		label_3.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.BOLD));
		label_3.setBounds(430, 25, 70, 25);
		label_3.setText("出发日期");
		
		Button button = new Button(composite, SWT.NONE);
		button.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		button.setBounds(634, 25, 80, 27);
		button.setText("查询");
		
		final CalendarCombo calendarCombo = new CalendarCombo(composite, SWT.NONE);
		calendarCombo.setBounds(506, 25, 88, 25);
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		table = new Table(composite_1, SWT.BORDER | SWT.FULL_SELECTION);
		table.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(124);
		tblclmnNewColumn.setText("车次");
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(125);
		tableColumn.setText("出发站");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(124);
		tableColumn_1.setText("目的站");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(125);
		tableColumn_2.setText("软卧余票");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(124);
		tableColumn_3.setText("硬卧余票");
		
		TableColumn tableColumn_4 = new TableColumn(table, SWT.NONE);
		tableColumn_4.setWidth(124);
		tableColumn_4.setText("硬座余票");
		sashForm.setWeights(new int[] {75, 383});
		
		DBHelper db=new DBHelper();
		List<Map<String,String>> list=new ArrayList<Map<String,String>>();
		String sql="select s_name from station";
		list=db.finds(sql, null);
	    for(int i=0;i<list.size();i++){
	    	combo.add(list.get(i).get("s_name"));
	    	combo_1.add(list.get(i).get("s_name"));
	    }

		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				table.removeAll();
				TableItem ti;
				TraininfoDBH traininfoDBH=new TraininfoDBH();
				TrainDBH trainDBH=new TrainDBH();
				String t_init=combo.getText().trim();
				String t_dest=combo_1.getText().trim();
				String dates=calendarCombo.getDateAsString().trim();
				List<Map<String,String>> list=new ArrayList<Map<String,String>>();
				List<Map<String,String>> list1=new ArrayList<Map<String,String>>();
				list=traininfoDBH.find(t_init, t_dest);
				list1=trainDBH.find(dates);
//				System.out.println(list1.size());
				for(Map map:list){
//					System.out.println(map);
//					System.out.println("=====================================");
					for(Map map1:list1){
//						System.out.println(map1);
//						System.out.println("------------------------------");
//						System.out.println(((String) map.get("t_id")).trim());
//						System.out.println(((String) map1.get("t_id")).trim());
						if(((String) map.get("t_id")).trim().equals(((String) map1.get("t_id")).trim())){
							ti=new TableItem(table,SWT.NONE);
//							System.out.println(String.valueOf( map.get("t_id")));
//							System.out.println("*************************************");
							double soft_seat=traininfoDBH.count(dates,(String)map.get("t_id"), t_init, t_dest, "软卧");
							double hard_seat=traininfoDBH.count(dates,(String)map.get("t_id"), t_init, t_dest, "硬卧");
							double t_seat=traininfoDBH.count(dates,(String)map.get("t_id"), t_init, t_dest, "硬座");
							System.out.println("soft_seat="+soft_seat);
							System.out.println("hard_seat="+hard_seat);
							System.out.println("t_seat="+t_seat);
								ti.setText(new String[]{String.valueOf( map.get("t_id")),String.valueOf(t_init),
										String.valueOf(t_dest),String.valueOf(soft_seat),String.valueOf(hard_seat),
										String.valueOf(t_seat)});
						}
					}
					
				}
				
			}
		});
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
