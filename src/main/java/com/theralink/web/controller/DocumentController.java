package com.theralink.web.controller;

import com.theralink.common.mapping.ModelMapperUtil;
import com.theralink.web.controller.BaseController;
import com.theralink.domain.Document;
import com.theralink.service.document.IDocumentService;
import com.theralink.web.viewModel.DocumentViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("document")
public class DocumentController extends BaseController {

	@Autowired
	private IDocumentService iDocumentService;

	@PostMapping(value = "/upload/{className}/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Boolean> upload(@RequestParam("file") MultipartFile file, @PathVariable String className, @PathVariable String id) throws IOException {
		Document doc = new Document(className, id, file.getOriginalFilename(), file.getContentType(), file.getBytes());
		String save = iDocumentService.save(doc);
		if (!save.isEmpty()) {
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Boolean>(false, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/list/{className}/{objectId}")
	public List<DocumentViewModel> list(@PathVariable String className, @PathVariable String objectId) {
		return ModelMapperUtil.mapList(iDocumentService.list(className, objectId), DocumentViewModel.class);
	}

	@DeleteMapping(value = "/delete/{id}")
	public boolean delete(@PathVariable String id) {
		return iDocumentService.deleteById(id);
	}

	@GetMapping("/download/{id}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String id) {
		try {
			// Retrieve the byte[] from the database
			Document document = iDocumentService.load(id);
			byte[] fileBytes = document.getData();

			// Convert byte[] to InputStream
			InputStream inputStream = new ByteArrayInputStream(fileBytes);

			// Create InputStreamResource
			InputStreamResource resource = new InputStreamResource(inputStream);

			// Set headers
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.setContentDisposition(ContentDisposition.builder("attachment").filename(document.getName()).build());

			// Return ResponseEntity
			return ResponseEntity.ok().headers(headers).body(resource);
		}
		catch (Exception e) {
			// Log the exception
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
