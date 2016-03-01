package br.com.seatecnologia.treinamento;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * Portlet implementation class HelloWorldPortlet
 * @author felipearaujo
 */
public class HelloWorldPortlet extends GenericPortlet {

    public void init() {
        editTemplate = getInitParameter("edit-template");
        helpTemplate = getInitParameter("help-template");
        viewTemplate = getInitParameter("view-template");
    }
    
    @Override
    public void processAction(ActionRequest request, ActionResponse response) throws PortletException, IOException{
    	PortletPreferences pref = request.getPreferences();
    	String nome = request.getParameter("nome");
		pref.setValue("NAME",nome );
		pref.store();
		response.setPortletMode(PortletMode.VIEW);
	 }

    public void doEdit(
            RenderRequest renderRequest, RenderResponse renderResponse)
        throws IOException, PortletException {

        include(editTemplate, renderRequest, renderResponse);
    }

    public void doHelp(
            RenderRequest renderRequest, RenderResponse renderResponse)
        throws IOException, PortletException {

        include(helpTemplate, renderRequest, renderResponse);
    }
    
    //A implementação padrão desse método, levanta uma exceção
    public void doView(
            RenderRequest renderRequest, RenderResponse renderResponse)
        throws IOException, PortletException {
    	PortletPreferences pref = renderRequest.getPreferences();
    	String valorPadrao = "";
    	//recuperando valor no preference
    	String nome = pref.getValue("NAME", valorPadrao);
    	renderRequest.setAttribute("ATTRIBUTE_NAME", nome);
        include(viewTemplate, renderRequest, renderResponse);
    }

    protected void include(
            String path, RenderRequest renderRequest,
            RenderResponse renderResponse)
        throws IOException, PortletException {

        PortletRequestDispatcher portletRequestDispatcher =
            getPortletContext().getRequestDispatcher(path);

        if (portletRequestDispatcher == null) {
            _log.error(path + " is not a valid include");
        }
        else {
            portletRequestDispatcher.include(renderRequest, renderResponse);
        }
    }
 
    protected String editTemplate;
    protected String helpTemplate;
    protected String viewTemplate;

    private static Log _log = LogFactoryUtil.getLog(HelloWorldPortlet.class);

}
