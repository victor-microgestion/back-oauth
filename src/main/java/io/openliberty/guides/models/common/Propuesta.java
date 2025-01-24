package io.openliberty.guides.models.common;

public class Propuesta {
    private String documento;
    private Condiciones condiciones;

    public static class Condiciones {
        private int diasGarantia;
        private int diasSoporte;
        private String habilesOCorridos;

        public int getDiasGarantia() {
            return diasGarantia;
        }

        public void setDiasGarantia(int diasGarantia) {
            this.diasGarantia = diasGarantia;
        }

        public int getDiasSoporte() {
            return diasSoporte;
        }

        public void setDiasSoporte(int diasSoporte) {
            this.diasSoporte = diasSoporte;
        }

        public String getHabilesOCorridos() {
            return habilesOCorridos;
        }

        public void setHabilesOCorridos(String habilesOCorridos) {
            this.habilesOCorridos = habilesOCorridos;
        }
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Condiciones getCondiciones() {
        return condiciones;
    }

    public void setCondiciones(Condiciones condiciones) {
        this.condiciones = condiciones;
    }
}