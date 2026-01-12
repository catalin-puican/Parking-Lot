package org.example.parkinglot1.common;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link org.example.parkinglot1.entities.CarPhoto}
 */
public class CarPhotoDto implements Serializable {
    private final Long id;
    private final byte[] fileContent;
    private final String fileType;
    private final String filename;

    public CarPhotoDto(Long id, String filename, String fileType,byte[] fileContent ) {
        this.id = id;
        this.fileContent = fileContent;
        this.fileType = fileType;
        this.filename = filename;
    }

    public Long getId() {
        return id;
    }

    public byte[] getFileContent() {
        return fileContent;
    }

    public String getFileType() {
        return fileType;
    }

    public String getFilename() {
        return filename;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarPhotoDto entity = (CarPhotoDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.fileContent, entity.fileContent) &&
                Objects.equals(this.fileType, entity.fileType) &&
                Objects.equals(this.filename, entity.filename);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fileContent, fileType, filename);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "fileContent = " + fileContent + ", " +
                "fileType = " + fileType + ", " +
                "filename = " + filename + ")";
    }
}