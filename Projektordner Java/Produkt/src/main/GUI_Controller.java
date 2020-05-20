package main;

import java.util.Random;

public class GUI_Controller
{
    public void changeFact()
    {
        String[] facts = {"Es gibt 25 Hörnchen Untergattungen.","Streifenhörnchen wiegen zwischen 30 und 120 Gramm.","Streifenhörnchen sind tagaktiv","Streifenhörnchen leben hauptsächlich in Wäldern.","Die Tunnel der Streifenhörnchen können über 3.5m lang werden.","Streifenhörnchen teilen ihre Tunnel in Schlaf- und Abfalltunnel auf."};
        String random = (facts[new Random().nextInt(facts.length)]);
        CCCC_Dlg.setTa_FactArea(random);
    }
}
