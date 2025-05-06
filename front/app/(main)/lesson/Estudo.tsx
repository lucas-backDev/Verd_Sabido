import { Button } from "@/components/ui/button";

const estudoConteudo = [
    {
      titulo: "Meio Ambiente 🌹",
      texto: "Os componentes bióticos são todos os seres vivos que compõem um ecossistema, como animais, plantas, fungos e microrganismos. Eles interagem entre si e com o ambiente ao seu redor, formando relações ecológicas essenciais para o funcionamento do sistema. O equilíbrio ecológico é mantido por meio dessas interações, como a cadeia alimentar, a reciclagem de nutrientes e o controle natural das populações. O ecossistema, por sua vez, exerce o papel de organizar e integrar os elementos vivos (bióticos) e não vivos (abióticos), garantindo condições para a sobrevivência das espécies. Já o meio ambiente é composto pela união dos fatores físicos (como solo, água, ar e clima) e biológicos (seres vivos), sendo o espaço onde essas relações acontecem e influenciam diretamente a vida dos organismos."
    }
  ];
  
const Estudo = ({ onStart }: { onStart: () => void }) => {
    return (
      <div className="p-4 max-w-xl mx-auto space-y-4">
        <h1 className="text-2xl font-bold">📚 Estudo</h1>
        {estudoConteudo.map((item, index) => (
          <div key={index} className="bg-white p-4 rounded">
            
            <div className="flex justify-center items-center">
              <h2 className="font-semibold text-2xl">{item.titulo}</h2>
            </div>
            <p className="text-gray-700 text-lg text-justify">{item.texto}</p>
          </div>
        ))}
        <div className="flex justify-center">
            <Button
              onClick={onStart}
              className="mt-4 align px-4 py-2 rounded transition"
              >
              Iniciar Quiz
            </Button>
          </div>
      </div>
    );
  };

  export default Estudo;
  