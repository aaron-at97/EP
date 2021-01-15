package data;

import java.util.Arrays;

public class DigitalSignature {
    private byte[] firma;

    public DigitalSignature (byte[] firm){
        this.firma = firm;
    }

    public byte[] getFirma() {
        return firma;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DigitalSignature signature = (DigitalSignature) o;
        return Arrays.equals(firma, signature.firma);
    }


    @Override
    public String toString() {
        return "DigitalSignature Signature{" + "Signature: '" + Arrays.toString(firma) + '\'' + '}';
    }
}

