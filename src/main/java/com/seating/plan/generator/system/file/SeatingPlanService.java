package com.seating.plan.generator.system.file;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class SeatingPlanService {

	@Value("${file.repository.path}")
	private String fileRepositoryPath;

	public String createSeatingPlanFile(List<Map<String, Object>> seatingPlanList, String examDate) throws FileNotFoundException, DocumentException {

		
		String filePath = fileRepositoryPath + "/" + UUID.randomUUID().toString() + ".pdf";

		float left = 40;
		float right = 40;
		float top = 40;
		float bottom = 40;
		Document document = new Document(PageSize.A4, left, right, top, bottom);


			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
			Font font15 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 15);
			Font font13 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 13);
			Font font11 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 11);
			Font font10 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10);
			Font font9 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 9);
			Font font8 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 8);
			Font font6 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 6);

			document.open();

			PdfPTable headerTable = new PdfPTable(1);

			PdfPCell headerCell = new PdfPCell(new Paragraph("SHRI G.S. INSTITUTE OF TECH. & Sc., INDORE", font15));
			headerCell.setPaddingLeft(10);
			headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			headerCell.setBorderWidth(0);
			headerTable.addCell(headerCell);

			PdfPCell headerCell2 = new PdfPCell(new Paragraph("UG/PG EXAM", font10));
			headerCell2.setPaddingLeft(10);
			headerCell2.setHorizontalAlignment(Element.ALIGN_CENTER);
			headerCell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
			headerCell2.setBorderWidth(0);
			headerTable.addCell(headerCell2);

			PdfPCell headerCell3 = new PdfPCell(new Paragraph("SEATING ARRANGEMENT FOR - " + examDate + " \n\n\n", font10));
			headerCell3.setPaddingLeft(10);
			headerCell3.setHorizontalAlignment(Element.ALIGN_CENTER);
			headerCell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
			headerCell3.setBorderWidth(0);
			headerTable.addCell(headerCell3);

			document.add(headerTable);

			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);

			String roomName = "";

			for (Map<String, Object> map : seatingPlanList) {

				if (roomName != (String) map.get("roomNo")) {
					PdfPTable seatingPlanHeaderTable = new PdfPTable(4);
					seatingPlanHeaderTable.setWidthPercentage(100);

					PdfPCell seatingPlanHeaderCell = new PdfPCell(new Paragraph("\nRoom No.", font10));
					// seatingPlanHeaderCell.setPaddingLeft(1);
					seatingPlanHeaderCell.setHorizontalAlignment(Element.ALIGN_LEFT);
					seatingPlanHeaderCell.setVerticalAlignment(Element.ALIGN_JUSTIFIED);
					seatingPlanHeaderCell.setBorderWidth(0);
					seatingPlanHeaderTable.addCell(seatingPlanHeaderCell);

					PdfPCell seatingPlanHeaderCell2 = new PdfPCell(new Paragraph("\nSubject", font10));
					// seatingPlanHeaderCell2.setPaddingLeft(1);
					seatingPlanHeaderCell2.setHorizontalAlignment(Element.ALIGN_LEFT);
					seatingPlanHeaderCell2.setVerticalAlignment(Element.ALIGN_JUSTIFIED);
					seatingPlanHeaderCell2.setBorderWidth(0);
					seatingPlanHeaderTable.addCell(seatingPlanHeaderCell2);

					PdfPCell seatingPlanHeaderCell3 = new PdfPCell(new Paragraph("\nRollnumber", font10));
					// seatingPlanHeaderCell3.setPaddingLeft(1);
					seatingPlanHeaderCell3.setHorizontalAlignment(Element.ALIGN_LEFT);
					seatingPlanHeaderCell3.setVerticalAlignment(Element.ALIGN_JUSTIFIED);
					seatingPlanHeaderCell3.setBorderWidth(0);
					seatingPlanHeaderTable.addCell(seatingPlanHeaderCell3);

					PdfPCell seatingPlanHeaderCell4 = new PdfPCell(new Paragraph("\nTotal", font10));
					// seatingPlanHeaderCell4.setPaddingLeft(1);
					seatingPlanHeaderCell4.setHorizontalAlignment(Element.ALIGN_LEFT);
					seatingPlanHeaderCell4.setVerticalAlignment(Element.ALIGN_JUSTIFIED);
					seatingPlanHeaderCell4.setBorderWidth(0);
					seatingPlanHeaderTable.addCell(seatingPlanHeaderCell4);

					document.add(seatingPlanHeaderTable);

					PdfPTable seatingPlanBorderTable = new PdfPTable(1);
					seatingPlanBorderTable.setWidthPercentage(100);

					PdfPCell seatingPlanBorderCell = new PdfPCell(
							new Paragraph("----------------------------------------------------------------------------------------------------------------------", font13));
					// seatingPlanHeaderCell.setPaddingLeft(1);
					seatingPlanBorderCell.setHorizontalAlignment(Element.ALIGN_LEFT);
					seatingPlanBorderCell.setVerticalAlignment(Element.ALIGN_JUSTIFIED);
					seatingPlanBorderCell.setBorderWidth(0);
					seatingPlanBorderTable.addCell(seatingPlanBorderCell);
					document.add(seatingPlanBorderTable);
				}

				PdfPTable seatingPlanDataTable = new PdfPTable(4);
				seatingPlanDataTable.setWidthPercentage(100);

				PdfPCell seatingPlanDataCell = new PdfPCell(new Paragraph((String) map.get("roomNo"), font9));
				// seatingPlanHeaderCell.setPaddingLeft(1);
				seatingPlanDataCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				seatingPlanDataCell.setVerticalAlignment(Element.ALIGN_JUSTIFIED);
				seatingPlanDataCell.setBorderWidth(0);
				seatingPlanDataTable.addCell(seatingPlanDataCell);

				PdfPCell seatingPlanDataCell2 = new PdfPCell(new Paragraph((String) map.get("subjectCode"), font9));
				// seatingPlanHeaderCell2.setPaddingLeft(1);
				seatingPlanDataCell2.setHorizontalAlignment(Element.ALIGN_LEFT);
				seatingPlanDataCell2.setVerticalAlignment(Element.ALIGN_JUSTIFIED);
				seatingPlanDataCell2.setBorderWidth(0);
				seatingPlanDataTable.addCell(seatingPlanDataCell2);

				PdfPCell seatingPlanDataCell3 = new PdfPCell(new Paragraph((String) map.get("seating"), font9));
				// seatingPlanHeaderCell3.setPaddingLeft(1);
				seatingPlanDataCell3.setHorizontalAlignment(Element.ALIGN_LEFT);
				seatingPlanDataCell3.setVerticalAlignment(Element.ALIGN_JUSTIFIED);
				seatingPlanDataCell3.setBorderWidth(0);
				seatingPlanDataTable.addCell(seatingPlanDataCell3);

				PdfPCell seatingPlanDataCell4 = new PdfPCell(new Paragraph((String) map.get("studentCount"), font9));
				// seatingPlanHeaderCell4.setPaddingLeft(1);
				seatingPlanDataCell4.setHorizontalAlignment(Element.ALIGN_LEFT);
				seatingPlanDataCell4.setVerticalAlignment(Element.ALIGN_JUSTIFIED);
				seatingPlanDataCell4.setBorderWidth(0);
				seatingPlanDataTable.addCell(seatingPlanDataCell4);

				document.add(seatingPlanDataTable);

				PdfPTable LineBrackTable = new PdfPTable(1);
				LineBrackTable.setWidthPercentage(100);

				PdfPCell lineBrackCell = new PdfPCell(new Paragraph("\n", font9));
				// seatingPlanHeaderCell.setPaddingLeft(1);
				lineBrackCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				lineBrackCell.setVerticalAlignment(Element.ALIGN_JUSTIFIED);
				lineBrackCell.setBorderWidth(0);
				LineBrackTable.addCell(lineBrackCell);
				document.add(LineBrackTable);

				roomName = (String) map.get("roomNo");

			}

			document.close();
			writer.close();

		
		return filePath;

	}
}
