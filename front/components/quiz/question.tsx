import React from "react";

interface PerguntaProps {
  descricao: string;
}

const Pergunta: React.FC<PerguntaProps> = ({ descricao }) => {
  return <h2>{descricao}</h2>;
};

export default Pergunta;
