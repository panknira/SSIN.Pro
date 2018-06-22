package kr.pankaj.tests.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * Servlet implementation class REST_TestInitial
 */

@WebServlet("/rest.test1")
public class REST_TestInitial extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public REST_TestInitial() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw = response.getWriter();
		pw.append("Served at: ");
		pw.append(request.getContextPath());
		String resp = collectRestResponse();
		
		pw.append(resp);
		
		pw.println("\n=============================================\n\n\t Next step is to parse JSON and get collector details");
	}

	private String collectRestResponse() {
		String URL = "https://api.surveymonkey.net/v3/surveys/152704688/details";
		String AUTH_KEY = "bearer F2RlLbeTeGLrDX.8BHgNte2IDI3Io2f10VUEZxRxyd-yCpkMwi8iC.vL6tHDI2LupQKhBjUMEprX9QmuICqcwt0VVgL70XREyjgNVquSLmtxPOukx8dmSOP-RYYHFwUS"; 
		String output = null;
		try {

			Client client = Client.create();

			WebResource webResource = client.resource(URL);

			ClientResponse response = webResource.header("Authorization", AUTH_KEY).header("Content-Type","application/json").get(ClientResponse.class);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

			output = response.getEntity(String.class);

			System.out.println("Output from Server .... \n");
			System.out.println(output);

		} catch (Exception e) {

			e.printStackTrace();

		}

		return output;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
