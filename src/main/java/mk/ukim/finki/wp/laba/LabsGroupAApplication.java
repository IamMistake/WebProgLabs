package mk.ukim.finki.wp.laba;

import mk.ukim.finki.wp.laba.repository.EventRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class LabsGroupAApplication {

	public static void main(String[] args) {
		SpringApplication.run(LabsGroupAApplication.class, args);
	}

}
