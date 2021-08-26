package model;

public enum EnumCargo implements StrategyCargo {
    CAIXA {
        @Override
        public int id() {
            return 1;
        }

        @Override
        public String nome() {
            return "Caixa de Mercado";
        }

        @Override
        public long tempoDescanso() {
            return 60;
        }

        @Override
        public double remuneracao() {
            return 10;
        }
    },
    YOUTUBER {
        @Override
        public int id() {
            return 2;
        }

        @Override
        public String nome() {
            return "Youtuber";
        }

        @Override
        public long tempoDescanso() {
            return 30;
        }

        @Override
        public double remuneracao() {
            return 6;
        }
    },
    PROGRAMADOR {
        @Override
        public int id() {
            return 3;
        }

        @Override
        public String nome() {
            return "Programador";
        }

        @Override
        public long tempoDescanso() {
            return 600;
        }

        @Override
        public double remuneracao() {
            return 90;
        }
    },
    ANALISTA {
        @Override
        public int id() {
            return 4;
        }

        @Override
        public String nome() {
            return "Analista de Software";
        }

        @Override
        public long tempoDescanso() {
            return 120;
        }

        @Override
        public double remuneracao() {
            return 210;
        }
    },
    EMPRESARIO {
        @Override
        public int id() {
            return 5;
        }

        @Override
        public String nome() {
            return "Empresário";
        }

        @Override
        public long tempoDescanso() {
            return 6000;
        }

        @Override
        public double remuneracao() {
            return 850;
        }
    };
}