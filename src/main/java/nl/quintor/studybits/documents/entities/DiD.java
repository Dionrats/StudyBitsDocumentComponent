package nl.quintor.studybits.documents.entities;

import lombok.Data;

@Data
public class DiD {
    private String didaddress;
    private String verkey;
    private String tempVerkey;
    private String metadata;
}
