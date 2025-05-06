"use client";

import React, { useEffect, useState } from "react";
import axios from "axios";
import Pergunta from "@/components/quiz/question";
import Alternativas from "@/components/quiz/alternative";
import { Button } from "@/components/ui/button";
import Estudo from "./Estudo";
import Confetti from 'react-confetti'

import { useWindowSize } from '@react-hook/window-size';


interface PerguntaType {
  id: number;
  descricao: string;
}

interface Alternativa {
  id: number;
  descricao: string;
  estaCorreta: boolean;
  codigoPergunta: number;
}

const Quiz = () => {
  const [perguntas, setPerguntas] = useState<PerguntaType[]>([]);
  const [alternativas, setAlternativas] = useState<Alternativa[]>([]);
  const [currentIndex, setCurrentIndex] = useState(0);
  const [selectedAlt, setSelectedAlt] = useState<number | null>(null);
  const [score, setScore] = useState(0);
  const [showResult, setShowResult] = useState(false);
  const [feedback, setFeedback] = useState<string | null>(null);
  const [isConfirmed, setIsConfirmed] = useState(false);
  const [ width, height ] = useWindowSize();
  
  const [modoEstudo, setModoEstudo] = useState(true);

  const currentQuestion = perguntas[currentIndex];

  useEffect(() => {
    axios.get("http://localhost:8080/api/pergunta")
      .then(res => setPerguntas(res.data))
      .catch(err => console.error(err));
  }, []);

  useEffect(() => {
    if (currentQuestion) {
      axios.get(`http://localhost:8080/api/alternativa/pergunta/${currentQuestion.id}`)
        .then(res => setAlternativas(res.data))
        .catch(err => console.error(err));
      }
  }, [currentQuestion]);

  const handleConfirm = () => {
    if (selectedAlt === null || isConfirmed) return;

    const altSelecionada = alternativas.find(alt => alt.id === selectedAlt);
    const acertou = altSelecionada?.estaCorreta;

    if (acertou) {
      setScore(score + 1);
      setFeedback("✅ Resposta correta!");
    } else {
      setFeedback("❌ Resposta incorreta.");
    }

    setIsConfirmed(true);
  };
  const resetaStatus = () => {
    setModoEstudo(true);
    setCurrentIndex(0);
    setScore(0);
    setSelectedAlt(null);
    setShowResult(false);
    setIsConfirmed(false);
    setFeedback(null);
  } 

  const handleNext = () => {
    if (currentIndex + 1 < perguntas.length) {
      setCurrentIndex(currentIndex + 1);
      setSelectedAlt(null);
      setIsConfirmed(false);
      setFeedback(null);
    } else {
      setShowResult(true);
    }
  };
  if (modoEstudo) return <Estudo onStart={() => setModoEstudo(false)} />;

  if (perguntas.length === 0) return <p>Carregando perguntas...</p>;

  const percenteAcertos = (score/perguntas.length);
  if (showResult && percenteAcertos >= 0.75) {
    return (
      <div>
        <Confetti width={width} height={height} gravity={0.3} numberOfPieces={250} />
        <p className="text-lg font-bold text-center mt-8">
          Você acertou {score} de {perguntas.length} perguntas!
        </p>
          <Button onClick={() => resetaStatus()}>Voltar</Button>
      </div>
    );
  } else if (showResult) {
    return ( 
      <div>
        <p className="text-lg font-bold text-center mt-8"> Você acertou {score} de {perguntas.length} perguntas!</p>
        <Button onClick={() => resetaStatus()}>Voltar</Button>
      </div>
    );
  }
  
  const progresso = ((currentIndex + 1) / perguntas.length) * 100;

  return (
    <div className="max-w-xl mx-auto p-4 border rounded shadow space-y-4 transition-all">
      <div className="text-sm text-gray-500">
        Pergunta {currentIndex + 1} de {perguntas.length}
        <div className="w-full bg-gray-200 h-2 rounded mt-1">
          <div className="bg-green-500 h-2 rounded" style={{ width: `${progresso}%` }}></div>
        </div>
      </div>

      <Pergunta descricao={currentQuestion.descricao} />
      <Alternativas
        alternativas={alternativas}
        selectedAlt={selectedAlt}
        onSelectAlt={setSelectedAlt}
        isConfirmed={isConfirmed}
      />

      {feedback && <p className={`font-semibold ${feedback.includes("✅") ? "text-green-600" : "text-red-600"}`}>{feedback}</p>}

      {!isConfirmed ? (
        <Button
          onClick={handleConfirm}
          className="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700 transition"
        >
          Confirmar
        </Button>
      ) : (
        <Button
          onClick={handleNext}
          className="bg-gray-600 text-white px-4 py-2 rounded hover:bg-gray-700 transition"
        >
          Próxima
        </Button>
      )}
    </div>
  );
};

export default Quiz;
