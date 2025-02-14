package userservice.services;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.DashedBorder;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import userservice.entities.Bill;
import userservice.repositories.BillRepository;
import userservice.utils.dtos.ArticleForBillDto;
import userservice.utils.dtos.BillDto;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor
public class EmailService {
    private final JavaMailSender emailSender;
    private final BillRepository billRepository;
    private final UserService userService;

//    public void sendSimpleMessage(String to, String subject, String text) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("jristic3620rn@raf.rs");
//        message.setTo(to);
//        message.setSubject(subject);
//        message.setText(text);
//        emailSender.send(message);
//    }
    @RabbitListener(queues = "billQueue")
    public void receiveMessage(BillDto billDto) throws FileNotFoundException, MessagingException {
        Bill bill = new Bill();
        bill.setCustomerId(userService.getById(billDto.getUserId()));
        bill.setAddress(billDto.getAddress());
        bill.setCardInfo(billDto.getCardInfo());
        bill.setDate(billDto.getDate());
        bill.setPrice(billDto.getPrice());
        createPdf(billDto, billRepository.save(bill));

        sendMailWithAttachment(bill.getCustomerId().getEmail(), "Drezzy Invoice", "Thank you for shopping with Drezzy! \n \n In attachment is your payment bill. \n For more information, feel fre to contact us! \n \n Your DREZZY");
    }


    public void sendMailWithAttachment(String to, String subject, String text) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("jristic3620rn@raf.rs");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text);

        helper.addAttachment("logo.png", new File("user-service/src/main/resources/files/drezzy.jpg"));
        helper.addAttachment("invoice.pdf", new File("user-service/src/main/resources/files/invoice.pdf"));

        emailSender.send(message);
    }

    public void createPdf(BillDto billDto, Bill bill) throws FileNotFoundException {
        String path="user-service/src/main/resources/files/invoice.pdf";
        PdfWriter pdfWriter=new PdfWriter(path);
        PdfDocument pdfDocument=new PdfDocument(pdfWriter);
        pdfDocument.setDefaultPageSize(PageSize.A4);
        Document document=new Document(pdfDocument);
        List<ArticleForBillDto> productList = billDto.getArticleForBillDtos();

        float threecol=190f;
        float twocol=285f;
        float twocol150=twocol+150f;
        float twocolumnWidth[]={twocol150, twocol};
        float threeColumnWidth[] = {threecol,threecol, threecol};
        float fullwidth[]={threecol*3};
        Paragraph onesp = new Paragraph("\n");

        Table table=new Table(twocolumnWidth);
        table.addCell(new Cell().add("Drezzy Invoice").setFontSize(20f).setBorder(Border.NO_BORDER).setBold());
        Table nestedtabe=new Table(new float[]{twocol/2, twocol/2});
        nestedtabe.addCell(new Cell().add("Invoice No.").setBold().setBorder(Border.NO_BORDER));
        nestedtabe.addCell(new Cell().add("DY" + generateRandomSixDigitNumber()).setBorder(Border.NO_BORDER));
        nestedtabe.addCell(new Cell().add("Invoice Date").setBold().setBold().setBorder(Border.NO_BORDER));
        nestedtabe.addCell(new Cell().add(formatDate(billDto.getDate())).setBorder(Border.NO_BORDER));

        table.addCell(new Cell().add(nestedtabe).setBorder(Border.NO_BORDER));

        Border gb=new SolidBorder(Color.GRAY,2f);
        Table divider = new Table(fullwidth);
        divider.setBorder(gb);
        document.add(table);
        document.add(onesp);
        document.add(divider);
        document.add(onesp);

        Table twoColTable=new Table(twocolumnWidth);
        twoColTable.addCell(getBillingandShippingCell("Shipping Information"));
        twoColTable.addCell(getBillingandShippingCell("Billing Information"));
        document.add(twoColTable.setMarginBottom(12f));

        Table twoColTable2=new Table(twocolumnWidth);
        twoColTable2.addCell(getCell10fLeft(  "Name",  true));
        twoColTable2.addCell(getCell10fLeft( "Card Holder",  true));
        twoColTable2.addCell(getCell10fLeft(  billDto.getCardInfo().getCardHolder(), false));
        twoColTable2.addCell(getCell10fLeft(billDto.getCardInfo().getCardHolder(), false));
        document.add(twoColTable2);

        Table twoColTable3=new Table(twocolumnWidth);
        twoColTable3.addCell(getCell10fLeft(  "Address",  true));
        twoColTable3.addCell(getCell10fLeft(  "Card ends with",  true));
        twoColTable3.addCell(getCell10fLeft(  billDto.getAddress().getStreet() + ", " + billDto.getAddress().getCity() +
                "\n" + billDto.getAddress().getState() + ", " + billDto.getAddress().getZip(),  false));
        twoColTable3.addCell(getCell10fLeft( "****" + billDto.getCardInfo().getCardNumber().substring(12,16),  false));
        document.add(twoColTable3);

        float oneColoumnwidth[]={twocol150};

        Table oneColTable1=new Table(oneColoumnwidth);
        oneColTable1.addCell(getCell10fLeft(  "Phone",  true));
        oneColTable1.addCell(getCell10fLeft(  billDto.getAddress().getPhone(),  false));
        oneColTable1.addCell(getCell10fLeft(  "Email",  true));
        oneColTable1.addCell(getCell10fLeft(  bill.getCustomerId().getEmail(),  false));
        document.add(oneColTable1.setMarginBottom(10f));

        Table tableDivider2=new Table(fullwidth);
        Border dgb=new DashedBorder(Color.GRAY,  0.5f);
        document.add(tableDivider2.setBorder(dgb));
        Paragraph producPara=new Paragraph(  "Products") ;

        document.add(producPara.setBold());
        Table threeColTable1=new Table(threeColumnWidth) ;
        threeColTable1.setBackgroundColor(Color.BLACK,  0.7f);

        threeColTable1.addCell(new Cell().add("Article"). setBold(). setFontColor(Color.WHITE).setBorder(Border.NO_BORDER));
        threeColTable1.addCell(new Cell().add("Quantity") . setBold(). setFontColor(Color. WHITE). setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));
        threeColTable1.addCell(new Cell().add("Price"). setBold(). setFontColor(Color. WHITE) . setTextAlignment (TextAlignment.RIGHT).setBorder(Border.NO_BORDER));
        document.add(threeColTable1);

        Table threeColTable2=new Table(threeColumnWidth);
        for (ArticleForBillDto article : productList){
            threeColTable2.addCell(new Cell().add(article.getTitle()).setBold().setBorder(Border.NO_BORDER).setMargin(10f));
            threeColTable2.addCell(new Cell().add("1").setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));
            threeColTable2.addCell(new Cell().add(String.valueOf(article.getPrice())).setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER)).setMargin(15f);
        }

        document.add(threeColTable2.setMarginBottom(20f));
        float onetwo[]={threecol+125f, threecol*2};
        Table threeColTable4=new Table(onetwo);
        threeColTable4.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
        threeColTable4.addCell(tableDivider2).setBorder(Border.NO_BORDER);
        document.add(threeColTable4);

        Table threeColTable3=new Table(threeColumnWidth);
        threeColTable3.addCell(new Cell().add("").setBorder(Border.NO_BORDER)).setMarginLeft(10f);
        threeColTable3.addCell(new Cell().add("Total"). setTextAlignment (TextAlignment. CENTER).setBorder(Border.NO_BORDER));
        threeColTable3.addCell(new Cell().add(String.valueOf(BigDecimal.valueOf(billDto.getPrice()).setScale(2, RoundingMode.HALF_UP).doubleValue())).setTextAlignment (TextAlignment. RIGHT).setBorder(Border.NO_BORDER)).setMargin(15f);

        document.add(threeColTable3);
        document.add(tableDivider2);
        document.add(new Paragraph( "\n"));
        document.add(divider.setBorder(new SolidBorder(Color.GRAY, 1)).setMarginBottom(15f));


        /// END ///
        document.close();
        System.out.println("PDF CREATED");
    }

    private Cell getCell10fLeft(String textValue,Boolean isBold){
        Cell myCell=new Cell().add(textValue).setFontSize(10f).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
        return isBold ?myCell.setBold():myCell;
    }

    private Cell getBillingandShippingCell(String textValue){
        return new Cell().add(textValue).setFontSize(12f).setBold().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
    }

    private String formatDate(Long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(new Date(timestamp));
    }

    private int generateRandomSixDigitNumber() {
        Random random = new Random();
        return 100000 + random.nextInt(900000); // Ensures a 6-digit number (100000-999999)
    }

}
