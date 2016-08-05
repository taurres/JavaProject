package com.wenjiaxi.oa.admin.workflow.action;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.GraphicInfo;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.apache.struts2.ServletActionContext;

/**
* 
* @author WEN JIAXI
* @date 2016年8月5日 上午9:56:02
* @version 1.0
*/

public class ProcessDiagramAction extends WorkflowAction{

	private static final long serialVersionUID = 6839035031243221572L;

	private String processInstanceId;
	
	
	public String showProcessDiagram(){
		try {
			ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery();
			//根据流程实例id获取流程实例
			ProcessInstance processInstance = processInstanceQuery.processInstanceId(processInstanceId).singleResult();
			//获流程定义id
			String processDefinitionId = processInstance.getProcessDefinitionId();
			//根据流程定义id获取流程图
			InputStream processDiagram = repositoryService.getProcessDiagram(processDefinitionId);
			//转换为bufferedimage
			BufferedImage image = ImageIO.read(processDiagram);
			Graphics2D g = (Graphics2D) image.getGraphics();
			g.setColor(Color.RED);
			g.setStroke(new BasicStroke(4.0f));
			//开启抗锯齿
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			
			//获取bpmnModel
			BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
			//获取当前活动的节点
			List<String> activeActivityIds = runtimeService.getActiveActivityIds(processInstanceId);
			for (String activeActivityId : activeActivityIds) {
				GraphicInfo gInfo = bpmnModel.getGraphicInfo(activeActivityId);
				g.drawRoundRect((int)gInfo.getX(), (int)gInfo.getY(), (int)gInfo.getWidth(), (int)gInfo.getHeight(), 10, 10);
			}
			
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("image/png");
			ImageIO.write(image, "png", response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}

	//getter setter
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	
	
}
