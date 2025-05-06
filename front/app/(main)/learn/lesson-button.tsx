import { Button } from "@/components/ui/button"
import Link from "next/link"

export const LessonButton = () => {
  return (
    <div className="h-[102px] w-[102px] relative">
      <div className="absolute -top-6 left-65.5 px-3 py-2.5 border-2 font-bold uppercase text-green-500 bg-white rounded-xl animate-bounce tracking-wide z-10">
        Start
        <div className="absolute left-1/2 -bottom-2 w-0 h-0 border-x-8 border-x-transparent border-t-8 transform -translate-x-1/2"></div>
      </div>
      <Link href="/lesson">
        <Button
        size="rounded"
        variant="secondary"
        className="h-[70px] w-[70px] border-b-8 -top-1 left-66.5 absolute"
        >
        </Button>
       </Link>
    </div>
  )
}