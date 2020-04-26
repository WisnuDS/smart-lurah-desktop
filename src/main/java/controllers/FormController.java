package controllers;

import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1CFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class FormController implements Initializable {

    @FXML
    private StackPane stackPane;

    @FXML
    private JFXTextField txtNomorSurat;

    @FXML
    private JFXTextField txtNama;

    @FXML
    private JFXTextField txtNomorKK;

    @FXML
    private JFXTextField txtTempatLahir;

    @FXML
    private DatePicker tglLahir;

    @FXML
    private JFXComboBox<String> jnsKelamin;

    @FXML
    private JFXComboBox<String> stsPerkawinan;

    @FXML
    private JFXComboBox<String> agama;

    @FXML
    private JFXComboBox<String> golDarah;

    @FXML
    private JFXTextField pekerjaan;

    @FXML
    private JFXTextArea alamat;

    @FXML
    private JFXComboBox<String> jenisSurat;

    @FXML
    private JFXButton btnBack;

    @FXML
    private JFXButton btnSubmit;

    @FXML
    private ImageView imgPreview;

    @FXML
    private JFXListView<String> listFile;

    @FXML
    void goBack(ActionEvent event) {
        changeView(event);
    }

    @FXML
    void submit(ActionEvent event) {
        JFXDialogLayout layout = new JFXDialogLayout();
        JFXDialog dialog = new JFXDialog(stackPane,layout, JFXDialog.DialogTransition.CENTER);
        layout.setHeading(new Text("Konfirmasi Input Data"));
        layout.setBody(new Text("Apakah anda yakin data yang telah dimasukan benar?"));
        JFXButton buttonYes = new JFXButton("Yakin");
        JFXButton buttonNo = new JFXButton("Tidak");
        buttonYes.setCursor(Cursor.HAND);
        buttonYes.setTextFill(Paint.valueOf("#ffffff"));
        buttonYes.setBackground(new Background(new BackgroundFill(Paint.valueOf("#4e73df"), CornerRadii.EMPTY,null)));
        buttonYes.setCursor(Cursor.HAND);
        buttonYes.setTextFill(Paint.valueOf("#ffffff"));
        buttonNo.setOnAction(event1 -> dialog.close());
        buttonYes.setOnAction(event1 -> {
            writeDocument();
            dialog.close();
            openDocument(FileSystems.getDefault().getPath("").toAbsolutePath() +"/"+txtNama.getText()+"_"+jenisSurat.getValue()+".pdf");
            Node node = (Node) event1.getSource();
            System.out.println(node.getParent().getParent().getParent().getParent().getParent().getParent().getParent().getChildrenUnmodifiable());
            AnchorPane pane = (AnchorPane) node.getParent().getParent().getParent().getParent().getParent().getParent().getParent();
            try {
                pane.getChildren().setAll((Node) FXMLLoader.load(getClass().getResource("../views/arrangement_view.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        layout.setActions(buttonNo,buttonYes);
        dialog.show();
    }

    private void changeView(ActionEvent event){
        Node node = (Node) event.getSource();
        System.out.println(node.getParent().getParent().getParent().getParent().getChildrenUnmodifiable());
        AnchorPane pane = (AnchorPane) node.getParent().getParent().getParent().getParent().getParent();
        try {
            pane.getChildren().setAll((Node) FXMLLoader.load(getClass().getResource("../views/arrangement_view.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeDocument() {
        String path = FileSystems.getDefault().getPath("").toAbsolutePath() + "/src/main/resources/views/resource/document/Pengantar.pdf";
        File file = new File(path);
        try {
            PDDocument doc = PDDocument.load(file);
            PDPage page = doc.getPage(0);
            PDPageContentStream stream = new PDPageContentStream(doc, page, AppendMode.APPEND, true, true);
            addingTextToPDf(stream, 300 - ((txtNomorSurat.getText().length() >> 1) * 5), 630, txtNomorSurat.getText());
            addingTextToPDf(stream, 301, 564, txtNama.getText());
            addingTextToPDf(stream, 301, 550, txtNomorKK.getText());
            addingTextToPDf(stream, 301, 536, txtTempatLahir.getText() + ", " + convertDate(tglLahir.getValue()));
            addingTextToPDf(stream, 301, 522, jnsKelamin.getValue());
            addingTextToPDf(stream, 301, 508, stsPerkawinan.getValue());
            addingTextToPDf(stream, 301, 494, agama.getValue());
            addingTextToPDf(stream, 301, 480, golDarah.getValue());
            addingTextToPDf(stream, 301, 466, pekerjaan.getText());
            addingTextToPDf(stream, 301, 452, alamat.getText());
            addingTextToPDf(stream, 71, 332, jenisSurat.getValue());
            String dateNow = "Banyuwangi, " + convertDate(LocalDate.now());
            addingTextToPDf(stream, 438 - ((dateNow.length() >> 1) * 5), 212, "Banyuwangi, " + convertDate(LocalDate.now()));
            stream.close();
            doc.save(new File(FileSystems.getDefault().getPath("").toAbsolutePath() +"/"+txtNama.getText()+"_"+jenisSurat.getValue()+".pdf"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openDocument(String path){
        if(Desktop.isDesktopSupported()){
            Desktop desktop = Desktop.getDesktop();
            new Thread(() -> {
                try {
                    desktop.open(new File(path));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    public void addingTextToPDf(PDPageContentStream stream, float x, float y, String text){
        try {
            stream.beginText();
            stream.setFont(PDType1Font.TIMES_ROMAN,12);
            stream.newLineAtOffset(x,y);
            stream.showText(text);
            stream.endText();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public String convertDate(LocalDate date){
        String month = null;
        switch (date.getMonthValue()){
            case 1:
                month = "Januari";
                break;
            case 2:
                month = "Februari";
                break;
            case 3:
                month = "Maret";
                break;
            case 4:
                month = "April";
                break;
            case 5:
                month = "Mei";
                break;
            case 6:
                month = "Juni";
                break;
            case 7:
                month = "Juli";
                break;
            case 8:
                month = "Agustus";
                break;
            case 9:
                month = "September";
                break;
            case 10:
                month = "Oktober";
                break;
            case 11:
                month = "November";
                break;
            case 12:
                month = "Desember";
                break;
        }
        return String.format("%d %s %d", date.getDayOfMonth(),month, date.getYear());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        jnsKelamin.getItems().add("Laki-Laki");
        jnsKelamin.getItems().add("Perempuan");
        stsPerkawinan.getItems().add("Belum Menikah");
        stsPerkawinan.getItems().add("Sudah Menikah");
        agama.getItems().addAll("Islam", "Kristen", "Katolik", "Hindu", "Budha", "Konghucu", "Aliran Kepercayaan");
        golDarah.getItems().addAll("A", "B", "AB", "O");
        jenisSurat.getItems().addAll("Surat Keterangan Pembuatan KTP bagi Pemula", "Surat Keterangan Kematian", "Surat Keterngan Pindah", "Surat Keterangan Datang", "Surat Perubahan KK", "Surat Pengantar SKCK", "Surat Pengantar Nikah", "Surat Keterangan Lahir");
    }
}
