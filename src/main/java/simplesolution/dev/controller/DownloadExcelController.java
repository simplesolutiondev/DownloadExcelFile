package simplesolution.dev.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.compress.utils.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import simplesolution.dev.exporter.ExcelFileExporter;
import simplesolution.dev.model.Customer;

@Controller
public class DownloadExcelController {
	
	@RequestMapping("/")
    public String index() {
        return "index";
    }
	
	@GetMapping("/download/customers.xlsx")
    public void downloadCsv(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=customers.xlsx");
        ByteArrayInputStream stream = ExcelFileExporter.contactListToExcelFile(createTestData());
        IOUtils.copy(stream, response.getOutputStream());
    }

	private List<Customer> createTestData(){
    	List<Customer> customers = new ArrayList<Customer>();
    	customers.add(new Customer("Vernon", "Barlow", "0123456789", "test1@simplesolution.dev"));
    	customers.add(new Customer("Maud", "Brock", "0123456788", "test2@simplesolution.dev"));
    	customers.add(new Customer("Chyna", "Cowan", "0123456787", "test3@simplesolution.dev"));
    	customers.add(new Customer("Krisha", "Tierney", "0123456786", "test4@simplesolution.dev"));
    	customers.add(new Customer("Sherry", "Rosas", "0123456785", "test5@simplesolution.dev"));
    	return customers;
    }
}
