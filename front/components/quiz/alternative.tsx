import React from "react";

interface Alternativa {
  id: number;
  descricao: string;
  estaCorreta: boolean;
  codigoPergunta: number;
}

interface Props {
  alternativas: Alternativa[];
  selectedAlt: number | null;
  onSelectAlt: (id: number) => void;
  isConfirmed: boolean;
}

const Alternativas: React.FC<Props> = ({ alternativas, selectedAlt, onSelectAlt, isConfirmed }) => {
  return (
    <ul className="space-y-2">
      {alternativas.map(alt => {
        let style = "border p-2 rounded cursor-pointer";
        if (isConfirmed) {
          if (alt.estaCorreta) style += " border-green-500 bg-green-100";
          else if (selectedAlt === alt.id) style += " border-red-500 bg-red-100";
          else style += " border-gray-300";
        } else {
          style += selectedAlt === alt.id ? " border-blue-500 bg-blue-100" : " border-gray-300";
        }

        return (
          <li key={alt.id} className={style} onClick={() => !isConfirmed && onSelectAlt(alt.id)}>
            <input
              type="radio"
              name="alternativa"
              checked={selectedAlt === alt.id}
              readOnly
              className="mr-2"
            />
            {alt.descricao}
          </li>
        );
      })}
    </ul>
  );
};

export default Alternativas;
