package JAX.RS.Client;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

public class APIConsumer {

	private static final String PERSON = "person";
	private static final String HELLO = "hello";
	private static final String REST = "rest";

	public static void main(String[] args) {

		callHelloWorldApi();

		callPersonApi();

	}

	private static void callHelloWorldApi() {
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);

		WebTarget target = client.target(getBaseURI());

		String response = target.path(REST).path(HELLO).request().accept(MediaType.TEXT_HTML).get(Response.class)
				.toString();

		String plainAnswer = target.path(REST).path(HELLO).request().accept(MediaType.TEXT_PLAIN).get(String.class);

		String htmlAnswer = target.path(REST).path(HELLO).request().accept(MediaType.TEXT_HTML).get(String.class);

		String jsonResponse = target.path("rest").path(PERSON).request().accept(MediaType.APPLICATION_XML)
				.get(String.class);

		System.out.println(jsonResponse);
		System.out.println(response);
		System.out.println(plainAnswer);
		System.out.println(htmlAnswer+"\n");
	}

	private static void callPersonApi() {
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);

		WebTarget target = client.target(getBaseURI());
		// Get XML
		String xmlResponse = target.path(REST).path(PERSON).request().accept(MediaType.TEXT_XML).get(String.class);
		// Get XML for application
		String xmlAppResponse = target.path(REST).path(PERSON).request().accept(MediaType.APPLICATION_XML)
				.get(String.class);

		// Get JSON for application
		String jsonResponse = target.path(REST).path(PERSON).request().accept(MediaType.APPLICATION_JSON)
				.get(String.class);

		System.out.println(xmlResponse);
		System.out.println(xmlAppResponse);
		System.out.println(jsonResponse);
	}

	public static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:8280/JAXRSApplication").build();
	}

}
