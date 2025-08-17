import { Link } from "react-router-dom";

export default function HeroBanner() {
    return (
        <section className="container" style={{ marginTop: 20 }}>
            <div className="hero-card hero-gradient">
                <div className="hero-inner">
                    <h1>Kidney Failure Risk — fast &amp; private</h1>
                    <p>Start with a quick calculation (no sign-in), or sign in to unlock full features.</p>

                    <div className="hero-actions">
                        <Link to="/quick" className="btn btn-primary">Quick calculation</Link>
                        <Link to="/login" className="btn btn-secondary">Sign in</Link>
                    </div>
                </div>
            </div>
        </section>
    );
}
