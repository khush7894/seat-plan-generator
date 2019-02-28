package com.seating.plan.generator.system.service;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FileService {

	private static final Logger logger = LoggerFactory.getLogger(FileService.class);

	public static final String IMAGE_SUFFIX = ".image";
	private static final String IMAGE_URI_TEMPLATE = "{0}/image/{1}.image";
	private static final String IMAGE_ENTITY_NAME_TEMPLATE = "{0}/image/{1}";

	@Value("${image.repository.path}")
	private String imageRepositoryPath;

	@Value("${image.max.allowed.size}")
	private long imageMaxAllowedSize;

	@PostConstruct
	public void init() {
		if (imageRepositoryPath == null) {
			logger.error("FileService: image repository path is not initialized. imageRepositoryPath={}", imageRepositoryPath);
			throw new IllegalStateException("FileService: image repository path is not initialized.");
		}

		File file = new File(imageRepositoryPath);
		if (!file.exists() || file.isFile()) {
			logger.info("Creating imageRepositoryPath={}", imageRepositoryPath);
			boolean mkdirs = file.mkdirs();
			logger.info("imageRepositoryPath={} created :{}", imageRepositoryPath, mkdirs);
		}
	}

	// remove start
	public File getImageFile(String entityName, Object id) {
		String path = FilenameUtils.concat(imageRepositoryPath, MessageFormat.format(IMAGE_URI_TEMPLATE, entityName, id));
		logger.debug("getting file path:{}", path);
		return new File(path);
	}

	public byte[] getImageData(String entityName, Object id) throws IOException {
		File file = getImageFile(entityName, id);
		return FileUtils.readFileToByteArray(file);
	}

	public String saveImage(String entityName, Object id, byte[] data) throws IOException {
		File file = getImageFile(entityName, id);
		logger.info("save image:{}", file);
		FileUtils.writeByteArrayToFile(file, data);
		return file.getAbsolutePath();
	}
	// remove end

	public byte[] getImageDataWithURI(String imageURI) throws IOException {
		String path = FilenameUtils.concat(imageRepositoryPath, imageURI);
		File file = new File(path);
		logger.debug("getting file path:{}", file);
		return FileUtils.readFileToByteArray(file);
	}

	public byte[] getImageDataWithName(String entityName, String imageName) throws IOException {
		String imageURI = MessageFormat.format(IMAGE_ENTITY_NAME_TEMPLATE, entityName, imageName);
		return getImageDataWithURI(imageURI);
	}

	public String saveImage1(String entityName, byte[] data) throws IOException {
		String imageURI = MessageFormat.format(IMAGE_URI_TEMPLATE, entityName, UUID.randomUUID().toString());
		String path = FilenameUtils.concat(imageRepositoryPath, imageURI);
		File file = new File(path);
		logger.info("save image:{}", file);
		FileUtils.writeByteArrayToFile(file, data);
		return imageURI;
	}

	public String getAbsolutePath(String fileURI) {
		return FilenameUtils.concat(imageRepositoryPath, fileURI);
	}

	public String getImageRepositoryPath() {
		return imageRepositoryPath;
	}

	public void setImageRepositoryPath(String imageRepositoryPath) {
		this.imageRepositoryPath = imageRepositoryPath;
	}

	public long getImageMaxAllowedSize() {
		return imageMaxAllowedSize;
	}

	public void setImageMaxAllowedSize(long imageMaxAllowedSize) {
		this.imageMaxAllowedSize = imageMaxAllowedSize;
	}

}
