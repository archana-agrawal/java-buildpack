import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

public class AnalyticServiceSender {
    public static void sendDataToAnalyticService(String emailAddress, LocalDate dateOfBirth) {
        try {
            // Construct the payload data
            String payload = "email=" + emailAddress + "&dob=" + dateOfBirth.toString();

            // Set up the connection to the analytic service API endpoint
            URL url = new URL("https://analytic-service.com/api/endpoint");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setDoOutput(true);

            // Send the payload data to the analytic service endpoint
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(payload.getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
            outputStream.close();

            // Check the response code to ensure the request was successful
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Data sent successfully to the analytic service.");
            } else {
                System.out.println("Failed to send data to the analytic service. Response code: " + responseCode);
            }

            // Close the connection
            connection.disconnect();
        } catch (Exception e) {
            System.out.println("An error occurred while sending data to the analytic service: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String emailAddress = "example@example.com";
        LocalDate dateOfBirth = LocalDate.of(1990, 5, 15);

        sendDataToAnalyticService(emailAddress, dateOfBirth);
    }
}
