package com.sevi.sptingtest;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductServiceControllerGildedRose {
	private static final int PRICE_INCREASE_LIMIT = 10;
	private static final int PRICE_INCREASE_VALUE = 10;
	private static final int TIME_OFFSET = 3600 * 1000; // 1 hour Millis
	private static Map<String, Product> productRepo = new LinkedHashMap<>();
	private static AtomicInteger atomicCounter = new AtomicInteger(0);
	private static ConcurrentMap<Integer, TimeRecord> viewLog = new ConcurrentHashMap<>();
	static {
		Product coin = new Product();
		coin.setId("5001");
		coin.setName("Coin");
		coin.setDescription("Italian silver coin XVII century");
		coin.setPrice(1000.99);
		productRepo.put(coin.getId(), coin);

		Product ring = new Product();
		ring.setId("5002");
		ring.setName("Dimond ring");
		ring.setDescription("Dimond ring XVII century");
		ring.setPrice(2000);

		Product pendant = new Product();
		pendant.setId("5003");
		pendant.setName("Emerald pendant");
		pendant.setDescription("Emerald pendant XVII century");
		pendant.setPrice(3000);

		productRepo.put(coin.getId(), coin);
		productRepo.put(ring.getId(), ring);
		productRepo.put(pendant.getId(), pendant);

		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				// TODO
				// Implement cleaner for viewLog
			}
		}, 5 * 60 * 1000, 5 * 60 * 1000);

	}

	@RequestMapping(value = "/grproducts/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@PathVariable("id") String id) {
		productRepo.remove(id);
		return new ResponseEntity<>("Product is deleted successsfully", HttpStatus.OK);
	}

	@RequestMapping(value = "/grproducts/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateProduct(@PathVariable("id") String id, @RequestBody Product product) {
		productRepo.remove(id);
		product.setId(id);
		productRepo.put(id, product);
		return new ResponseEntity<>("Product is updated successsfully", HttpStatus.OK);
	}

	@RequestMapping(value = "/grproducts", method = RequestMethod.POST)
	public ResponseEntity<Object> createProduct(@RequestBody Product product) {
		productRepo.put(product.getId(), product);
		return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);
	}

	@RequestMapping(value = "/grproducts")
	public ResponseEntity<Object> getProduct() {
		Map<String, Product> products = new HashMap<String, Product>();
		Iterator<?> it = productRepo.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Product> e = (Map.Entry<String, Product>) it.next();
			products.put(e.getKey(), getProductWithPriceAdjustment(e.getValue()));
		}
		return new ResponseEntity<>(products.values(), HttpStatus.OK);
	}

	@RequestMapping(value = "/grproduct/{id}")
	public ResponseEntity<Object> getProduct(@PathVariable("id") String id) {
		// Add record to the log when product is viewed
		TimeRecord timeOfRecord = new TimeRecord(id, new Date());
		Integer next = atomicCounter.incrementAndGet();
		viewLog.put(next, timeOfRecord);
		return new ResponseEntity<>(getProductWithPriceAdjustment(productRepo.get(id)), HttpStatus.OK);
	}

	private Product getProductWithPriceAdjustment(Product p) {
		Product product = null;
		int count = 0;
		Iterator<TimeRecord> it = viewLog.values().iterator();
		while (it.hasNext()) {
			TimeRecord tr = (TimeRecord) it.next();
			Date timeWithOffset = new Date(System.currentTimeMillis() - TIME_OFFSET);
			if (p.getId().equals(tr.getProductId()) && tr.getTimeOfRecord().after(timeWithOffset)) {
				count++;
			}
		}
		if (count > PRICE_INCREASE_LIMIT) {
			try {
				product = (Product) p.clone();
				product.setPrice(product.getPrice() + product.getPrice() * PRICE_INCREASE_VALUE / 100);
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			product = p;
		}
		return product;
	}

}


