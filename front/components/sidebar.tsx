import { cn } from "@/lib/utils";
import Image from "next/image";
import Link from "next/link";
import { SidebarItem } from "./sidebar-item";

type Props = {
    className?: string;
};

export const Sidebar = ({ className }: Props) => {
    return (
        <div className={cn(
            "flex h-full lg:w-[256px] lg:fixed left-0 top-0 px-4 border-r-2 flex-col",
            className,
        )}>
            <Link href="/learn">
                <div className="pt-8 pl-4 pb-7 flex items-center gap-x-3">
                    <Image src="/mascote.png" height={60} width={60} alt="mascote" />
                    <h1 className="text-2xl font-extrabold text-green-600 tracking-wide">
                        Verd Sabido
                    </h1>
                </div>
            </Link>
            <div className="flex flex-col gap-2">
                <SidebarItem 
                label="APRENDER" 
                href="/learn"
                iconSrc="/learn.svg"
                />
                <SidebarItem 
                label="RANKING" 
                href="/ranking"
                iconSrc="/trophy.svg"
                />
                <SidebarItem 
                label="MISSÕES" 
                href="/quests"
                iconSrc="/quest.svg"
                />
                <SidebarItem 
                label="LOJA" 
                href="/shop"
                iconSrc="/shop.svg"
                />
            </div>
        </div>
    )
}