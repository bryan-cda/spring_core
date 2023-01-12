package br.com.spring.webflux;

import br.com.spring.webflux.domain.Quote;
import br.com.spring.webflux.repository.QuoteRepository;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.Date;

import static java.lang.String.format;

@EnableScheduling
@SpringBootApplication
@Log4j2
public class WebFluxApplication {
	@Autowired
	private QuoteRepository quoteRepository;

	public static void main(String[] args) {
		SpringApplication.run(WebFluxApplication.class, args);
	}

	@Scheduled(fixedDelay = 1000)
	public void generationData(){
		log.info(format("Quote: [%s]", quoteRepository.findFirstBySymbolOrderByTimestampDesc("foo")
				.map(this::transformData)
				.orElseGet(this::initData)));


		log.info(format("Timestamp: [%s]", LocalDateTime.now()));
	}

	private Quote initData() {
		return quoteRepository.save(Quote.builder()
				.symbol("foo")
				.openValue(0.2)
				.closeValue(0.3)
				.timestamp(new Date())
				.build());
	}

	private Quote transformData(Quote quote) {
		return quoteRepository.save(Quote.builder()
				.symbol(quote.getSymbol())
				.openValue(quote.getOpenValue() * new RandomDataGenerator().nextUniform(-0.10, 0.10))
				.closeValue(quote.getCloseValue() * new RandomDataGenerator().nextUniform(-0.10, 0.10))
				.timestamp(new Date())
				.build());
	}
}
