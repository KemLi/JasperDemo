package servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import utils.JasperHelper;

public class BaobiaoServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	// STEP 1 : 查询数据
		List datas = getData();
	// STEP 2 : 指定数据源
		JRDataSource datasource = new JRBeanCollectionDataSource(datas);
	// STEP 3 : 指定模板文件
		ServletContext context = req.getSession().getServletContext();
		File reportFile = null;
		reportFile = new File(context.getRealPath("InfraductionReceive.jasper"));
		String exportFilePath = "tempPath";
	// STEP 4 : 输出报表不同输出方式，具体实现在后边注释
		
		/* 使用HTML输出，表现为页面直接显示报表
		try {
			
			JasperHelper.showHtml(exportFilePath, reportFile.getPath(), req,
					resp, new HashMap(), datasource);
		} catch (JRException e) {
			e.printStackTrace();
		} */
		
		/* 使用PDF输出，表现为出现PDF文件的下载对话框 */
		JasperHelper.export("pdf", "pdf1", reportFile, req, resp,new HashMap(), datasource);
		/* 使用EXCEL输出，表现为出现EXCEL文件的下载对话框 */
		//JasperHelper.export("excel", "excel1", reportFile, req, resp,new HashMap(), datasource);
		
	}

	public static List getData() {
		List datas = new ArrayList();
		Map map1 = new HashMap();
		map1.put("id", "1");
		map1.put("name", "aaa");
		map1.put("email", "111@qq.com");
		map1.put("qq", "111");

		Map map2 = new HashMap();
		map2.put("id", "2");
		map2.put("name", "bbb");
		map2.put("email", "222@qq.com");
		map2.put("qq", "222");

		Map map3 = new HashMap();
		map3.put("id", "3");
		map3.put("name", "ccc");
		map3.put("email", "333@qq.com");
		map3.put("qq", "333");

		datas.add(map1);
		datas.add(map2);
		datas.add(map3);
		return datas;
	}
}
