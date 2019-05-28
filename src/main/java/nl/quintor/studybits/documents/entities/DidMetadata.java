package nl.quintor.studybits.documents.entities;

import lombok.Data;

@Data
public class DidMetadata {
    private String did;
    private String verkey;
    private String tempVerkey;
    private String metadata;
}
