import QuickCalcCard from '../components/QuickCalcCard';
import InfoStrip from '../components/InfoStrip';
import HeroBanner from "../components/HeroBanner.tsx";

export default function Home() {
    return (
        <>
            {/* Hero */}
            <HeroBanner />

            {/* Content */}
            <section id="quick" style={{padding: '22px 0 10px'}}>
                <div className="container" style={{display: 'grid', gap: 18, gridTemplateColumns: '1.8fr 1fr'}}>
                    <QuickCalcCard/>
                    <div className="card" style={{display: 'none'}}/>
                </div>
            </section>

            <InfoStrip/>
        </>
    );
}
