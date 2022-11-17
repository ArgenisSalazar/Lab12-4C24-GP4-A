package com.tecsup.petclinic.services;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tecsup.petclinic.controllers.PetControllerTest;

@SpringBootTest
	public class OwnerServiceTest {
		private static final Logger logger = LoggerFactory.getLogger(PetControllerTest.class);

		private static final ObjectMapper om = new ObjectMapper();
		
		@Autowired
		private MockMvc mockMvc;

		@Test
		public void testFindAllOwners() throws Exception {
	
			int ID_FIRST = 1;
	  
			this.mockMvc.perform(get("/pets"))
			.andExpect(status().isOk()) // HTTP 200
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$[0].id", is(ID_FIRST)));
		}


		/**
		 * 
		 * @throws Exception
		 * 
		 */
		@Test
		public void testFindOwnerOK() throws Exception {

			int ID_SEARCH = 1;
				String NOMBRE_OWNER = "Piero";
				String APELLIDO_OWNER = "Apaza";
				String CIUDAD_OWNER = "Lima";
				String DIA_REF = "2010-07-05";



				mockMvc.perform(get("/pets/" + ID_SEARCH))  // Finding object with ID = 1
					.andExpect(content().contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.id", is(1)))
					.andExpect(jsonPath("$.nombre", is(NOMBRE_OWNER)))
					.andExpect(jsonPath("$.apellido", is(APELLIDO_OWNER)))
					.andExpect(jsonPath("$.ciudad", is(CIUDAD_OWNER)))
					.andExpect(jsonPath("$.birthDate", is(DIA_REF)));

		}

		/**
		 * 
		 * @throws Exception
		 * 
		 */
		@Test
		public void testFindOwnerOK2() throws Exception {

			int ID_SEARCH = 2;
				String NOMBRE_OWNER = "Antonio";
				String APELLIDO_OWNER = "Godoy";
				String CIUDAD_OWNER = "Arequipa";
				String DIA_REF = "2003-11-02";



				mockMvc.perform(get("/pets/" + ID_SEARCH))  // Finding object with ID = 1
					.andExpect(content().contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.id", is(1)))
					.andExpect(jsonPath("$.nombre", is(NOMBRE_OWNER)))
					.andExpect(jsonPath("$.apellido", is(APELLIDO_OWNER)))
					.andExpect(jsonPath("$.ciudad", is(CIUDAD_OWNER)))
					.andExpect(jsonPath("$.birthDate", is(DIA_REF)));

		}
		
		/**
		 * 
		 * @throws Exception
		 */
		@Test
		public void testFindOwnerKO() throws Exception {

			int ID_SEARCH = 666;


			mockMvc.perform(get("/owner/" + ID_SEARCH)) // Finding object with ID = 666
				.andExpect(status().isNotFound());
			
		}


		public static Logger getLogger() {
			return logger;
		}


		public static ObjectMapper getOm() {
			return om;
		}

}
