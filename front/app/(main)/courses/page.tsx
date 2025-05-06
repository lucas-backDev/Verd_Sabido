import { Button } from "@/components/ui/button";
import { NotebookText } from "lucide-react";
import Link from "next/link";

const CoursesPage = () => { 
    return (
    <div className="h-full max-w-[912px] px-3 mx-auto">
        <h1 className="text-2xl font-bold text-neutral-700">
        <div className="w-full rounded-xl bg-green-500 p-5 text-white flex items-center justify-between">
      <div className="space-y-2.5">
        <h3 className="text-2xl font-bold">
          Meio Ambiente
        </h3>
        <p className="text-lg">
          Aprenda os primeiros passos para a preservação.
        </p>
      </div>
      <Link href="/learn">
        <Button
        size="lg"
        variant="secondary"
        className="hidden lg:flex border-2 border-b-4 active:border-b-2"
        >
          <NotebookText className="mr-2" />
          Entrar
        </Button>
      </Link>
    </div>
        </h1>
    </div>
);
};

export default CoursesPage;