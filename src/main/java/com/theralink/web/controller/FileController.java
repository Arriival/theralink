package com.theralink.web.controller;

import com.theralink.common.mapping.ModelMapperUtil;
import com.theralink.domain.File;
import com.theralink.service.file.IFileService;
import com.theralink.web.controller.BaseController;
import com.theralink.web.viewModel.file.FileViewModel;
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
@RequestMapping("file")
public class FileController extends BaseController {

	@Autowired
	private IFileService iFileService;

	@PostMapping(value = "/upload/{className}/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Boolean> upload(@RequestParam("file") MultipartFile file, @PathVariable String className, @PathVariable String id) throws IOException {
		File doc = new File(className, id, file.getOriginalFilename(), file.getContentType(), file.getBytes());
		String save = iFileService.save(doc);
		if (!save.isEmpty()) {
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Boolean>(false, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/list/{className}/{objectId}")
	public List<FileViewModel> list(@PathVariable String className, @PathVariable String objectId) {
		return ModelMapperUtil.mapList(iFileService.list(className, objectId), FileViewModel.class);
	}

	@DeleteMapping(value = "/delete/{id}")
	public boolean delete(@PathVariable String id) {
		return iFileService.deleteById(id);
	}

	@GetMapping("/download/{id}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String id) {
		try {
			File document = iFileService.load(id);
			byte[] fileBytes = document.getData();
			InputStream inputStream = new ByteArrayInputStream(fileBytes);
			InputStreamResource resource = new InputStreamResource(inputStream);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.setContentDisposition(ContentDisposition.builder("attachment").filename(document.getName()).build());
			return ResponseEntity.ok().headers(headers).body(resource);
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
