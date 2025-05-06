import { Button } from "@/components/ui/button";
import Image from "next/image";
import Link from "next/link";

export const Footer = () => {
    return (
       <footer className="hidden lg:block h-20 w-full border-t-2 border-slate-200 p-2">
        <div className="max-w-screen-lg mx-auto flex items-center justify-evenly h-full">
            <Link href="https://github.com/lucas-backDev/Verd_Sabido">
                <Button size="icon" variant="ghost" className="w-full">
                    <Image 
                    src="/gh.svg" 
                    alt="projectRepos" 
                    height={32} 
                    width={40}
                    className="mr-4 rounded-md"
                    />
                </Button>
            </Link>
        </div>
       </footer> 
    )
}

export default Footer;