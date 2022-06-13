package de.tastaturathlet.document.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.OffsetDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Document {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @CreatedBy
  @Column(name = "doc_created_user", nullable = false, length = 50, updatable = false)
  @JsonIgnore
  private String createUser;

  @CreatedDate
  @Column(name = "doc_created_date", updatable = false)
  @JsonIgnore
  private OffsetDateTime createDate;

  @Column(name = "doc_published_date", nullable = false)
  private OffsetDateTime publishedDate;

  @Column(name = "doc_language_code", nullable = false)
  private String languageCode;

  @Column(name = "doc_content_title")
  private String contentTitle;

  @Column(name = "doc_content_text", nullable = false, columnDefinition = "text")
  private String contentText;

  @Column(name = "doc_content_url")
  private String contentUrl;

  @Column(name = "doc_content_thumbnail_url")
  private String contentThumbnailUrl;

  @Column(name = "doc_source_name")
  private String sourceName;

  public Document() {

  }

  public Long getId() {
    return id;
  }

  public String getCreateUser() {
    return createUser;
  }

  public OffsetDateTime getCreateDate() {
    return createDate;
  }

  public OffsetDateTime getPublishedDate() {
    return publishedDate;
  }

  public void setPublishedDate(OffsetDateTime publishedDate) {
    this.publishedDate = publishedDate;
  }

  public String getLanguageCode() {
    return languageCode;
  }

  public void setLanguageCode(String languageCode) {
    this.languageCode = languageCode;
  }

  public String getContentTitle() {
    return contentTitle;
  }

  public void setContentTitle(String contentTitle) {
    this.contentTitle = contentTitle;
  }

  public String getContentText() {
    return contentText;
  }

  public void setContentText(String contentText) {
    this.contentText = contentText;
  }

  public String getContentUrl() {
    return contentUrl;
  }

  public void setContentUrl(String contentUrl) {
    this.contentUrl = contentUrl;
  }

  public String getContentThumbnailUrl() {
    return contentThumbnailUrl;
  }

  public void setContentThumbnailUrl(String contentThumbnailUrl) {
    this.contentThumbnailUrl = contentThumbnailUrl;
  }

  public String getSourceName() {
    return sourceName;
  }

  public void setSourceName(String sourceName) {
    this.sourceName = sourceName;
  }

  /**
   * The default implementation of equals() in the Object class says that equality is the same as object identity, and
   * income and expenses are two distinct instances. Java SE defines the contract that our implementation of the
   * equals() method must fulfill. Most of the criteria are common sense. The equals() method must be:
   * <p>
   * reflexive: an object must equal itself symmetric: x.equals(y) must return the same result as y.equals(x)
   * transitive: if x.equals(y) and y.equals(z), then also x.equals(z) consistent: the value of equals() should change
   * only if a property that is contained in equals() changes (no randomness allowed)
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Document document = (Document) o;
    return id == document.id && Objects.equals(publishedDate, document.publishedDate)
        && Objects.equals(languageCode, document.languageCode) && Objects.equals(contentTitle,
        document.contentTitle) && Objects.equals(contentUrl, document.contentUrl) && Objects.equals(
        sourceName, document.sourceName);
  }

  /**
   * hashCode() returns an integer representing the current instance of the class. We should calculate this value
   * consistent with the definition of equality for the class. Thus, if we override the equals() method, we also have to
   * override hashCode().
   */
  @Override
  public int hashCode() {
    return Objects.hash(id, publishedDate, languageCode, contentTitle, contentUrl, sourceName);
  }

  @Override
  public String toString() {
    return "Document{" +
        "id=" + id +
        ", createUser='" + createUser + '\'' +
        ", createDate=" + createDate +
        ", publishedDate=" + publishedDate +
        ", languageCode='" + languageCode + '\'' +
        ", contentTitle='" + contentTitle + '\'' +
        ", contentText='" + contentText + '\'' +
        ", contentUrl='" + contentUrl + '\'' +
        ", contentThumbnailUrl='" + contentThumbnailUrl + '\'' +
        ", sourceName='" + sourceName + '\'' +
        '}';
  }
}
